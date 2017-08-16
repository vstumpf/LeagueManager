package com.vstumpf.lolmanager.controller;

import com.vstumpf.lolmanager.dto.UserDto;
import com.vstumpf.lolmanager.model.User;
import com.vstumpf.lolmanager.repository.UserRepository;
import com.vstumpf.lolmanager.security.JwtTokenUtil;
import com.vstumpf.lolmanager.security.JwtUser;
import com.vstumpf.lolmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Vincent on 7/23/2017.
 */

@RestController
public class UserController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService users;

    @GetMapping(value = "user")
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }
    @GetMapping(value = "user/{userId}")
    public UserDto getUserByUserId(@PathVariable long userId) {
        //System.out.println(users.getOne(userId).toString());
        return users.getUserById(userId);
    }
}
