package com.vstumpf.lolmanager.service;

import com.vstumpf.lolmanager.dto.UserDto;
import com.vstumpf.lolmanager.dto.UserOwnedDto;
import com.vstumpf.lolmanager.dto.UserRegisterDto;
import com.vstumpf.lolmanager.model.Role;
import com.vstumpf.lolmanager.model.RoleName;
import com.vstumpf.lolmanager.model.User;
import com.vstumpf.lolmanager.repository.RoleRepository;
import com.vstumpf.lolmanager.repository.UserRepository;
import com.vstumpf.lolmanager.validator.UserRegisterDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vincent on 8/4/2017.
 */

@Service
public class UserService {
    @Autowired
    private UserRepository users;

    @Autowired
    private RoleRepository roles;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User convertToEntity(UserDto userDto) {
        return users.getOne(userDto.getId());
    }

    public UserDto convertToDto(User user) {
        return new UserDto(user);
    }

    public UserDto postNewUser(UserRegisterDto userRegisterDto) {
        User u = new User();
        u.setFirstName(userRegisterDto.getFirstName());
        u.setLastName(userRegisterDto.getLastName());
        u.setUsername(userRegisterDto.getUsername());
        u.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        u.setRoles(Arrays.asList(roles.findRoleByRole(RoleName.ROLE_USER)));
        u.setLastPasswordResetDate(new Date());
        u.setIso(null);
        u.setEnabled(true);
        users.save(u);
        return new UserOwnedDto(u);
    }

    public UserDto getUserByIdOwn(long id) {
        return new UserOwnedDto(users.getOne(id));
    }

    public UserDto getUserByIdOther(long id) {
        return new UserDto(users.getOne(id));
    }

    public List<UserDto> getUsers(Pageable pageable) {
        return users.findByEnabled(true, pageable).stream().map(UserDto::new).collect(Collectors.toList());
    }

    public List<UserDto> getUsersAdmin(Pageable pageable) {
        return users.findAll(pageable).getContent().stream().map(UserOwnedDto::new).collect(Collectors.toList());
    }
}
