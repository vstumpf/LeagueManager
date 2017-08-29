package com.vstumpf.lolmanager.validator;

import com.vstumpf.lolmanager.dto.ErrorDto;
import lombok.Getter;
import lombok.Setter;

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
}
