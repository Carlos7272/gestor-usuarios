package com.organization.user_manager.api.controller.error_handler;

import com.organization.user_manager.api.model.response.BaseErrorResponse;
import com.organization.user_manager.api.model.response.ErrorResponse;
import com.organization.user_manager.util.exceptions.CreateException;
import com.organization.user_manager.util.exceptions.UpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorController {

    @ExceptionHandler({CreateException.class, UpdateException.class})
    public BaseErrorResponse handleInternalServerError(RuntimeException exception) {
        return ErrorResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
    }
}