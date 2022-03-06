package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.StorageException;
import com.zahaand.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void updateResume(Integer index, Resume resume) {
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
    protected Resume getResume(Integer index) {
        return storage[index];
    }

    @Override
    protected void deleteResume(Integer index) {
        System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
        storage[size - 1] = null;
        size--;
    }

    @Override
    public List<Resume> getAllResumesAsList() {
        Resume[] resumesArray = new Resume[size];
        List<Resume> resumesList = new ArrayList<>();
        System.arraycopy(storage, 0, resumesArray, 0, size);
        Collections.addAll(resumesList, resumesArray);
        return resumesList;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    protected abstract void insertElement(Resume resume);
}
