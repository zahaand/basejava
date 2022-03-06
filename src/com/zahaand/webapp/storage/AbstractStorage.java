package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.Resume;

import java.util.List;

public abstract class AbstractStorage<KeyType> implements Storage {

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        KeyType key = getSearchKey(uuid);
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
        KeyType key = getSearchKeyIfExist(uuid);
        updateResume(key, r);
        System.out.println(uuid + " SUCCESSFULLY UPDATED");
    }

    @Override
    public Resume get(String uuid) {
        if (getSearchKeyIfExist(uuid) != null) {
            return getResume(getSearchKey(uuid));
        }
        return null;
    }

    @Override
    public void delete(String uuid) {
        KeyType key = getSearchKeyIfExist(uuid);
        deleteResume(key);
        System.out.println(uuid + " SUCCESSFULLY DELETED");
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumesList = getAllResumesAsList();
        resumesList.sort(Resume::compareTo);
        return resumesList;
    }

    private KeyType getSearchKeyIfExist(String uuid) {
        KeyType key = getSearchKey(uuid);
        if (isExist(key)) {
            return key;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void saveResume(Resume resume);

    protected abstract void updateResume(KeyType key, Resume resume);

    protected abstract Resume getResume(KeyType key);

    protected abstract void deleteResume(KeyType key);

    protected abstract KeyType getSearchKey(String uuid);

    protected abstract boolean isExist(KeyType key);

    protected abstract List<Resume> getAllResumesAsList();
}
