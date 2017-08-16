package com.vstumpf.lolmanager.dto;

import com.vstumpf.lolmanager.model.Role;
import com.vstumpf.lolmanager.model.RoleName;
import com.vstumpf.lolmanager.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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
        users = new ArrayList<>();
        for (User u : r.getUsers()) {
            users.add(new RoleUserDto(u));
        }
    }

    @Data
    private class RoleUserDto {
        private long id;
        private String username;
        public RoleUserDto(User u) {
            this.id = u.getId();
            this.username = u.getUsername();
        }
    }
}
