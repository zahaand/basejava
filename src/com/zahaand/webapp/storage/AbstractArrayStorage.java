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
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void updateResume(Object getKey, Resume resume) {
        int index = (int) getKey;
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
    protected Resume getResume(Object getKey) {
        int index = (int) getKey;
        return storage[index];
    }

    @Override
    protected void deleteResume(Object getKey) {
        int index = Integer.parseInt(getKey.toString());
        System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
        storage[size - 1] = null;
        size--;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void insertElement(Resume resume);

    @Override
    protected boolean checkKey(Object key) {
        return (int) key >= 0;
    }
}
