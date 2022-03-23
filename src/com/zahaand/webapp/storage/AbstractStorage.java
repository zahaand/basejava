package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.Resume;

import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<KeyType> implements Storage {

    protected static final Logger LOGGER = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void save(Resume r) {
        LOGGER.info("Save " + r.getUuid());
        String uuid = r.getUuid();
        KeyType searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            saveResume(searchKey, r);
            LOGGER.info("Successfully saved " + r.getUuid());
        } else {
            LOGGER.warning(uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
    }

    @Override
    public void update(Resume r) {
        LOGGER.info("Update " + r.getUuid());
        String uuid = r.getUuid();
        KeyType searchKey = getSearchKeyIfExist(uuid);
        updateResume(searchKey, r);
        LOGGER.info("Successfully updated " + r.getUuid());
    }

    @Override
    public Resume get(String uuid) {
        LOGGER.info("GET " + uuid);
        if (getSearchKeyIfExist(uuid) != null) {
            return getResume(getSearchKey(uuid));
        }
        return null;
    }

    @Override
    public void delete(String uuid) {
        LOGGER.info("Delete " + uuid);
        KeyType searchKey = getSearchKeyIfExist(uuid);
        deleteResume(searchKey);
        LOGGER.info("Successfully deleted " + uuid);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOGGER.info("Get all sorted");
        List<Resume> resumesList = getAllResumesAsList();
        resumesList.sort(Resume::compareTo);
        return resumesList;
    }

    private KeyType getSearchKeyIfExist(String uuid) {
        KeyType searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            return searchKey;
        } else {
            LOGGER.warning(uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void saveResume(KeyType searchKey, Resume resume);

    protected abstract void updateResume(KeyType searchKey, Resume resume);

    protected abstract Resume getResume(KeyType searchKey);

    protected abstract void deleteResume(KeyType searchKey);

    protected abstract KeyType getSearchKey(String uuid);

    protected abstract boolean isExist(KeyType searchKey);

    protected abstract List<Resume> getAllResumesAsList();
}
