package com.organization.user_manager.util.exceptions;

public class UpdateException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Error updating user %s";

    public UpdateException(String tableName) {
        super(String.format(ERROR_MESSAGE, tableName));
    }
}
