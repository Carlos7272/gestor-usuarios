package com.organization.user_manager.util.exceptions;

public class CreateException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Error saving user %s";

    public CreateException(String tableName) {
        super(String.format(ERROR_MESSAGE, tableName));
    }
}