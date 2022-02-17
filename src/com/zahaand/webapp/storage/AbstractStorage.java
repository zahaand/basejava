package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume r) {
            String uuid = r.getUuid();
        if (!checkResume(r)) {
            saveResume(r);
            System.out.println(uuid + " SUCCESSFULLY SAVED");
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    @Override
    public void update(Resume r) {
            String uuid = r.getUuid();
            Object key = getKey(uuid);
        if (checkResume(r)) {
            updateResume(key, r);
            System.out.println(uuid + " SUCCESSFULLY UPDATED");
        }
    }

    @Override
    public Resume get(String uuid) {
            Object key = getKey(uuid);
        if (checkResume(new Resume(uuid))) {
            return getResume(key);
        }
        return null;
    }

    @Override
    public void delete(String uuid) {
        Object key = getKey(uuid);
        if (checkResume(new Resume(uuid))) {
            deleteResume(key);
            System.out.println(uuid + " SUCCESSFULLY DELETED");
        }
    }

    private Object getKey(String uuid) {
        Object key = searchKey(uuid);
        if (checkResume(new Resume(uuid))) {
            return key;
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract void saveResume(Resume resume);

    protected abstract void updateResume(Object searchKey, Resume resume);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract Object searchKey(Object uuid);

    protected abstract boolean checkResume(Resume resume);
}
