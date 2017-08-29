package com.vstumpf.lolmanager.service;

import com.vstumpf.lolmanager.security.JwtUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by Vincent on 8/23/2017.
 */
@Service
public class CurrentService {

    private CurrentService() {
        super();
    }

    public long getId() {
        return ((JwtUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    public boolean isAdmin() {
        return ((JwtUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).isAdmin();
    }
}
