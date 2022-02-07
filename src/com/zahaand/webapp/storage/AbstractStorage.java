package com.zahaand.webapp.storage;

public abstract class AbstractStorage implements Storage {
    protected abstract int getIndex(String uuid);
}
