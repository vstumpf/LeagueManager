package com.vstumpf.lolmanager.validator;

import com.vstumpf.lolmanager.dto.UserRegisterDto;
import com.vstumpf.lolmanager.repository.UserRepository;
import com.vstumpf.lolmanager.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Created by Vincent on 8/26/2017.
 */

@Component
public class UserRegisterDtoValidator implements Validator {

    @Autowired
    private UserRepository users;

    @Autowired
    private ValidatorUtils validatorUtils;

    public boolean supports(Class<?> clazz) {
        return UserRegisterDto.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        UserRegisterDto user = (UserRegisterDto) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", ErrorCode.MISSING_FIELD.mes());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", ErrorCode.MISSING_FIELD.mes());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", ErrorCode.MISSING_FIELD.mes());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", ErrorCode.MISSING_FIELD.mes());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", ErrorCode.MISSING_FIELD.mes());

        if (errors.hasErrors())
            return;

        if (!user.getPassword().equals(user.getPasswordConfirm()))
            errors.rejectValue("passwordConfirm", ErrorCode.PASSWORD_MISMATCH.mes());
        if (!validatorUtils.isValidPassword(user.getPassword()))
            errors.rejectValue("password", ErrorCode.BAD_VALUE.mes());
        if (!validatorUtils.isEmail(user.getUsername()))
            errors.rejectValue("username", ErrorCode.BAD_VALUE.mes());

        if (errors.hasErrors())
            return;

        if (users.findByUsername(user.getUsername()) != null)
            errors.rejectValue("username", ErrorCode.DUPLICATE_USERNAME.mes());

    }
}
