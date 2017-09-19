package com.vstumpf.lolmanager.service;

import com.vstumpf.lolmanager.model.User;
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
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    public boolean isAdmin() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).isAdmin();
    }

    public boolean isOwnerOrAdmin(long id) {
        return getId() == id || isAdmin();
    }
}
