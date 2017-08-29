package com.vstumpf.lolmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.tags.form.ErrorsTag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 8/26/2017.
 */
public class ErrorDto {

    @Getter
    @Setter
    private HttpStatus status;
    @Getter
    private List<ErrorTag> errors;

    public ErrorDto() {
        errors = new ArrayList<>();
    }

    public ErrorDto(HttpStatus status) {
        this.status = status;
        errors = new ArrayList<>();
    }

    public ErrorDto addError(String error, String message) {
        errors.add(new ErrorTag(error, message));
        return this;
    }

    //public ErrorDto addErrors(Error)

    @Data
    @AllArgsConstructor
    private class ErrorTag {
        private String error;
        private String message;
    }
}
