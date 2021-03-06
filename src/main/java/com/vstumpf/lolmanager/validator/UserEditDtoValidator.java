package com.vstumpf.lolmanager.validator;

import com.vstumpf.lolmanager.dto.UserEditDto;
import com.vstumpf.lolmanager.repository.UserRepository;
import com.vstumpf.lolmanager.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserEditDtoValidator implements Validator {
    @Autowired
    private UserRepository users;

    @Autowired
    private ValidatorUtils validatorUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean supports(Class<?> clazz) {
        return UserEditDto.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        UserEditDto u = (UserEditDto) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", ErrorCode.MISSING_FIELD.mes());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", ErrorCode.MISSING_FIELD.mes());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordOld", ErrorCode.MISSING_FIELD.mes());

        if (errors.hasErrors())
            return;

        if (!validatorUtils.isValidPassword(u.getPassword()))
            errors.rejectValue("password", ErrorCode.BAD_VALUE.mes());
        if (!u.getPassword().equals(u.getPasswordConfirm()))
            errors.rejectValue("password", ErrorCode.PASSWORD_MISMATCH.mes());

        if (errors.hasErrors())
            return;

        if (!passwordEncoder.matches(u.getPasswordOld(), users.getOne(u.getId()).getPassword()))
            errors.rejectValue("passwordOld", ErrorCode.PASSWORD_MISMATCH.mes());
    }


}
