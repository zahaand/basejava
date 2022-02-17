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
    protected void updateResume(Object searchKey, Resume resume) {
        int index = (int) searchKey;
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
    protected Resume getResume(Object searchKey) {
        int index = (int) searchKey;
        return storage[index];
    }

    @Override
    protected void deleteResume(Object searchKey) {
        int index = Integer.parseInt(searchKey.toString());
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
    protected boolean checkResume(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(resume)) {
                return true;
            }
        }
        return false;
    }
}
