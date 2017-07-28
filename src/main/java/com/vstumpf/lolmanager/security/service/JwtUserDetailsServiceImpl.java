package com.vstumpf.lolmanager.security.service;

import com.vstumpf.lolmanager.model.User;
import com.vstumpf.lolmanager.repository.UserRepository;
import com.vstumpf.lolmanager.security.JwtUserFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Vincent on 7/24/2017.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    private static final Log logger = LogFactory.getLog(JwtUserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }

}
