package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.StorageException;
import com.zahaand.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;


    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    protected void clearStorage() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void updateResume(Resume resume) {
        int index = getIndex(resume.getUuid());
        storage[index] = resume;
    }

    @Override
    protected void saveResume(Resume resume) {
        if (size != storage.length) {
            insertElement(resume);
            size++;
        } else {
            throw new StorageException("STORAGE OVERFLOW ", resume.getUuid() + " DO NOT SAVE");
        }
    }


    @Override
    protected Resume getResume(String uuid) {
        int index = getIndex(uuid);
        return storage[index];
    }

    @Override
    protected void deleteResume(String uuid) {
        int index = getIndex((uuid));
        System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume[] getAllResumes() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected int getStorageSize() {
        return size;
    }

    protected abstract void insertElement(Resume resume);
}
