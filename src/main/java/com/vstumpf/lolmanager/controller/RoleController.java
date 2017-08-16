package com.vstumpf.lolmanager.controller;

import com.vstumpf.lolmanager.dto.RoleDto;
import com.vstumpf.lolmanager.model.Role;
import com.vstumpf.lolmanager.repository.RoleRepository;
import com.vstumpf.lolmanager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vincent on 7/30/2017.
 */
@RestController
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping(value = "role/{roleId}")
    public RoleDto getRoleByRoleId(@PathVariable int roleId) {
        return roleService.getRoleById(roleId);
    }
}
