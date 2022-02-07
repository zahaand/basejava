package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume resume);
}
