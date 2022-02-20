package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    private String uuid;
    private Object key;

    @Override
    public void save(Resume r) {
        uuid = r.getUuid();
        key = getSearchKey(uuid);
        if (!isExist(key)) {
            saveResume(r);
            System.out.println(uuid + " SUCCESSFULLY SAVED");
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    @Override
    public void update(Resume r) {
        uuid = r.getUuid();
        key = getSearchKey(uuid);
        if (isExist(key)) {
            updateResume(key, r);
            System.out.println(uuid + " SUCCESSFULLY UPDATED");
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public Resume get(String uuid) {
        key = getSearchKey(uuid);
        if (isExist(key)) {
            return getResume(key);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void delete(String uuid) {
        key = getSearchKey(uuid);
        if (isExist(key)) {
            deleteResume(key);
            System.out.println(uuid + " SUCCESSFULLY DELETED");
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void saveResume(Resume resume);

    protected abstract void updateResume(Object searchKey, Resume resume);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);
}
