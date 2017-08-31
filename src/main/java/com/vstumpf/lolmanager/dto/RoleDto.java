package com.vstumpf.lolmanager.dto;

import com.vstumpf.lolmanager.model.Role;
import com.vstumpf.lolmanager.model.RoleName;
import com.vstumpf.lolmanager.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vincent on 8/4/2017.
 */
@Data
public class RoleDto {
    private int id;
    private RoleName role;
    private List<RoleUserDto> users;

    public RoleDto(Role r) {
        this.id = r.getId();
        this.role = r.getRole();
        users = r.getUsers().stream().map(RoleUserDto::new).collect(Collectors.toList());

        /*
        users = new ArrayList<>();
        for (User u : r.getUsers()) {
            users.add(new RoleUserDto(u));
        }
        */
    }

    @Data
    private class RoleUserDto {
        private long id;
        private String username;
        private String firstName;
        private String lastName;
        private RoleUserDto(User u) {
            this.id = u.getId();
            this.username = u.getUsername();
            this.firstName = u.getFirstName();
            this.lastName = u.getLastName();
        }
    }
}
