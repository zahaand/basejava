package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void insertElement(Resume resume) {
        storage[size] = resume;
    }

    @Override
    protected Object searchKey(Object uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
