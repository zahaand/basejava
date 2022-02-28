package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.StorageException;
import com.zahaand.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

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
    protected void updateResume(Object index, Resume resume) {
        storage[(int) index] = resume;
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
    protected Resume getResume(Object index) {
        return storage[(int) index];
    }

    @Override
    protected void deleteResume(Object index) {
        int i = (int) index;
        System.arraycopy(storage, i + 1, storage, i, size - (i + 1));
        storage[size - 1] = null;
        size--;
    }

    @Override
    public List<Resume> sortStorage() {
        Arrays.sort(storage, Resume::compareTo);
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage,0, resumes, 0, size);
        return Arrays.asList(resumes);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void insertElement(Resume resume);

    @Override
    protected boolean isExist(Object index) {
        return (int) index >= 0;
    }
}
