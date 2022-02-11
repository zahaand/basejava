package com.zahaand.webapp.exception;

public class StorageException extends RuntimeException {
    private final String uuid;

    // constructor with accompany message
    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
