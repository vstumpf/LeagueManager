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
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private List<UserRoleDto> roles;

    public UserDto (User u) {
        this.id = u.getId();
        this.username = u.getUsername();
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.roles = new ArrayList<>();
        for (Role r : u.getRoles()) {
            this.roles.add(new UserRoleDto(r));
        }
    }

    @Data
    private class UserRoleDto{
        private int id;
        private RoleName role;
        public UserRoleDto(Role r) {
            this.id = r.getId();
            this.role = r.getRole();
        }
    }
}
