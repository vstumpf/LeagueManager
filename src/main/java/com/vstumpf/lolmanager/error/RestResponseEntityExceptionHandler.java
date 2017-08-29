package com.vstumpf.lolmanager.error;

import com.vstumpf.lolmanager.dto.ErrorDto;
import com.vstumpf.lolmanager.validator.ValidatorException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 8/26/2017.
 */

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public RestResponseEntityExceptionHandler() {
        super();
    }

    @ExceptionHandler({ValidatorException.class})
    public ResponseEntity<Object> handleBadRequest(
            final ValidatorException e,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {
        final ErrorDto err = e.getErrorDto();
        return handleExceptionInternal(e, err, headers, err.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ErrorDto err = new ErrorDto(HttpStatus.BAD_REQUEST);
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            err.addError(error.getCode(), error.getField());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            err.addError(error.getCode(), error.getObjectName());
        }
        return handleExceptionInternal(ex, err, headers, err.getStatus(), request);


    }
}
