package com.vstumpf.lolmanager.security;

import com.vstumpf.lolmanager.model.User;
import com.vstumpf.lolmanager.utils.JwtTokenUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vincent on 7/24/2017.
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        String authToken = request.getHeader(this.tokenHeader);

        String username = jwtTokenUtil.getUsernameFromToken(authToken);

        logger.info("Checking authentication for user " + username + " | URI: " + request.getRequestURI());

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load user details from Database
            User user = (User) this.userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(authToken, user)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }

}
