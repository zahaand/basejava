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
        if (isExistKey(uuid)) {
            updateResume(key, r);
            System.out.println(uuid + " SUCCESSFULLY UPDATED");
        }
    }

    @Override
    public Resume get(String uuid) {
        Object key = getSearchKey(uuid);
        if (isExistKey(uuid)) {
            return getResume(key);
        }
        return null;
    }

    @Override
    public void delete(String uuid) {
        Object key = getSearchKey(uuid);
        if (isExistKey(uuid)) {
            deleteResume(key);
            System.out.println(uuid + " SUCCESSFULLY DELETED");
        }
    }

    private boolean isExistKey(String uuid) {
        Object key = getSearchKey(uuid);
        if (isExist(key)) {
            return true;
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
