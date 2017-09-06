package com.vstumpf.lolmanager.validator;

import com.vstumpf.lolmanager.dto.ErrorDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

/**
 * Created by Vincent on 8/26/2017.
 */
public class ValidatorException extends Exception {
    @Getter
    @Setter
    private ErrorDto errorDto;
    public ValidatorException() {
        super();
    }

    public ValidatorException(ErrorDto e) {
        super();
        this.errorDto = e;
    }

    public ValidatorException(BindingResult errs) {
        super();
        this.errorDto = new ErrorDto(HttpStatus.BAD_REQUEST).addErrors(errs);
    }
}
