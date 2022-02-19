package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        Object key = getSearchKey(uuid);
        if (!isExist(key)) {
            saveResume(r);
            System.out.println(uuid + " SUCCESSFULLY SAVED");
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    @Override
    public void update(Resume r) {
        String uuid = r.getUuid();
        Object key = getSearchKey(uuid);
        if (isExist(key)) {
            updateResume(key, r);
            System.out.println(uuid + " SUCCESSFULLY UPDATED");
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public Resume get(String uuid) {
        Object key = getSearchKey(uuid);
        if (isExist(key)) {
            return getResume(key);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void delete(String uuid) {
        Object key = getSearchKey(uuid);
        if (isExist(key)) {
            deleteResume(key);
            System.out.println(uuid + " SUCCESSFULLY DELETED");
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void saveResume(Resume resume);

    protected abstract void updateResume(Object getSearchKey, Resume resume);

    protected abstract Resume getResume(Object getSearchKey);

    protected abstract void deleteResume(Object getSearchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object getSearchKey);
}
