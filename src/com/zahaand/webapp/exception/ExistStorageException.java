package com.zahaand.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("ALREADY EXIST ", uuid);
    }
}
