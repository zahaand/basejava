package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.StorageException;
import com.zahaand.webapp.model.Resume;

import java.util.Arrays;
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
    protected void saveResume(Integer index, Resume resume) {
        if (size != storage.length) {
            insertElement(index, resume);
            size++;
            LOGGER.info("Successfully saved " + resume.getUuid());
        } else {
            LOGGER.warning("Storage overflow");
            LOGGER.info(resume + " do not save");
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
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    protected abstract void insertElement(int index, Resume resume);
}
