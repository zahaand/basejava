package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        String uuid = r.getUuid();
        int index = getIndex(uuid);
        if (index >= 0) {
            updateResume(index, r);
            System.out.println(uuid + " SUCCESSFULLY UPDATED");
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        int index = getIndex(uuid);
        if (index < 0) {
            saveResume(r);
            System.out.println(uuid + " SUCCESSFULLY SAVED");
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return getResume(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteResume(index);
            System.out.println(uuid + " SUCCESSFULLY DELETED");
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void updateResume(Object index, Resume resume);

    protected abstract void saveResume(Resume resume);

    protected abstract Resume getResume(Object index);

    protected abstract void deleteResume(Object index);

    protected abstract int getIndex(String uuid);
}
