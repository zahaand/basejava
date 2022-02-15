package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        String uuid = r.getUuid();
        if (containsKey(uuid)) {
            updateResume(searchKey(uuid), r);
            System.out.println(uuid + " SUCCESSFULLY UPDATED");
        }
    }

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        int key = searchKey(uuid);
        if (key < 0) {
            saveResume(r);
            System.out.println(uuid + " SUCCESSFULLY SAVED");
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    @Override
    public Resume get(String uuid) {
        if (containsKey(uuid)) {
            return getResume(searchKey(uuid));
        }
        return null;
    }

    @Override
    public void delete(String uuid) {
        if (containsKey(uuid)) {
            deleteResume(searchKey(uuid));
            System.out.println(uuid + " SUCCESSFULLY DELETED");
        }
    }

    private boolean containsKey (String uuid) {
        int key = searchKey(uuid);
        if (key >= 0) {
            return true;
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract void updateResume(Object searchKey, Resume resume);

    protected abstract void saveResume(Resume resume);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract int searchKey(Object uuid);
}
