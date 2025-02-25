package com.organization.user_manager.api.controller.error_handler;

import com.organization.user_manager.api.model.response.BaseErrorResponse;
import com.organization.user_manager.api.model.response.ErrorResponse;
import com.organization.user_manager.util.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestController {

    @ExceptionHandler({IdNotFoundException.class})
    public BaseErrorResponse handleIdNotFound(RuntimeException exception) {
        return ErrorResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }
}