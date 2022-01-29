package com.zahaand.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("NOT EXIST ", uuid);
    }
}
