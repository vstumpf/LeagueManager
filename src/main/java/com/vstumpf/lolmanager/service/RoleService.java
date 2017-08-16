package com.vstumpf.lolmanager.service;

import com.vstumpf.lolmanager.dto.RoleDto;
import com.vstumpf.lolmanager.model.Role;
import com.vstumpf.lolmanager.model.User;
import com.vstumpf.lolmanager.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Vincent on 8/4/2017.
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository roles;

    public Role convertToEntity(RoleDto roleDto) {
        return roles.getOne(roleDto.getId());
    }

    public RoleDto convertToDto(Role role) {
        return new RoleDto(role);
    }

    public RoleDto getRoleById(int id) {
        return new RoleDto(roles.getOne(id));
    }
}
