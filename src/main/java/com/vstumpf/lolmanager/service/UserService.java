package com.vstumpf.lolmanager.service;

import com.vstumpf.lolmanager.dto.UserDto;
import com.vstumpf.lolmanager.model.User;
import com.vstumpf.lolmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Vincent on 8/4/2017.
 */

@Service
public class UserService {
    @Autowired
    private UserRepository users;

    public User convertToEntity(UserDto userDto) {
        return users.getOne(userDto.getId());
    }

    public UserDto convertToDto(User user) {
        return new UserDto(user);
    }

    public UserDto getUserById(long id) {
        return new UserDto(users.getOne(id));
    }
}
