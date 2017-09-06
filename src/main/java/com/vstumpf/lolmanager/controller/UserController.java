package com.vstumpf.lolmanager.controller;

import com.vstumpf.lolmanager.dto.UserDto;
import com.vstumpf.lolmanager.dto.UserEditDto;
import com.vstumpf.lolmanager.dto.UserRegisterDto;
import com.vstumpf.lolmanager.error.PermissionException;
import com.vstumpf.lolmanager.model.User;
import com.vstumpf.lolmanager.repository.UserRepository;
import com.vstumpf.lolmanager.security.JwtTokenUtil;
import com.vstumpf.lolmanager.security.JwtUser;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


/**
 * Created by Vincent on 7/23/2017.
 */

@RestController
@RequestMapping("/users")
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

    /*
    @GetMapping(value = "user")
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }*/

    @GetMapping(value = "")
    public List<UserDto> getUsers(@PageableDefault Pageable pageable) {
        if (currentService.isAdmin()) {
            return users.getUsersAdmin(pageable);
        }
        return users.getUsers(pageable);
    }

    @PostMapping
    public UserDto postUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        return users.postNewUser(userRegisterDto);
    }

    @GetMapping(value = "/{userId}")
    public UserDto getUserByUserId(@PathVariable long userId) {
        if (currentService.isOwnerOrAdmin(userId))
            return users.getUserByIdOwn(userId);

        return users.getUserByIdOther(userId);
    }

    @PutMapping(value = "/{userId}")
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
