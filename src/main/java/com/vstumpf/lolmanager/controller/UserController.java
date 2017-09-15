package com.vstumpf.lolmanager.controller;

import com.vstumpf.lolmanager.dto.UserDto;
import com.vstumpf.lolmanager.dto.UserEditDto;
import com.vstumpf.lolmanager.dto.UserRegisterDto;
import com.vstumpf.lolmanager.security.JwtTokenUtil;
import com.vstumpf.lolmanager.service.CurrentService;
import com.vstumpf.lolmanager.service.UserService;
import com.vstumpf.lolmanager.validator.UserEditDtoValidator;
import com.vstumpf.lolmanager.validator.UserRegisterDtoValidator;
import com.vstumpf.lolmanager.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * Created by Vincent on 7/23/2017.
 */

@RestController
public class UserController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CurrentService currentService;

    @Autowired
    private UserService users;

    @Autowired
    private UserRegisterDtoValidator userRegisterDtoValidator;

    @Autowired
    private UserEditDtoValidator userEditDtoValidator;

    @InitBinder("userRegisterDto")
    protected void initUserRegisterDtoBinder (final WebDataBinder binder) {
        binder.addValidators(userRegisterDtoValidator);
    }


    @GetMapping(value = "user")
    public UserDto getAuthenticatedUser() {
        return users.getUserByIdOwn(currentService.getId());
    }

    @GetMapping(value = "/users")
    public List<UserDto> getUsers(@PageableDefault Pageable pageable) {
        if (currentService.isAdmin()) {
            return users.getUsersAdmin(pageable);
        }
        return users.getUsers(pageable);
    }

    @PostMapping(value = "/users")
    public UserDto postUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        return users.postNewUser(userRegisterDto);
    }

    @GetMapping(value = "/users/{userId}")
    public UserDto getUserByUserId(@PathVariable long userId) {
        if (currentService.isOwnerOrAdmin(userId))
            return users.getUserByIdOwn(userId);

        return users.getUserByIdOther(userId);
    }

    @PutMapping(value = "/users/{userId}")
    @PreAuthorize("@currentService.isOwnerOrAdmin(#userId)")
    public UserDto putUserByUserId(@PathVariable long userId,
                                   @RequestBody UserEditDto userEditDto,
                                   BindingResult bindingResult)
        throws ValidatorException {
        userEditDto.setId(userId);
        userEditDtoValidator.validate(userEditDto, bindingResult);
        if (bindingResult.hasErrors())
            throw new ValidatorException(bindingResult);
        return users.putUserById(userId, userEditDto);

    }

}
