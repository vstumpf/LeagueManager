package com.vstumpf.lolmanager.repository;

import com.vstumpf.lolmanager.model.Role;
import com.vstumpf.lolmanager.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Vincent on 7/30/2017.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByRole(RoleName roleName);
}
