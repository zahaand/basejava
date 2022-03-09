package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.Resume;

import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<T> implements Storage {

    private static final Logger LOGGER = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void save(Resume r) {
        LOGGER.info("Save " + r);
        String uuid = r.getUuid();
        T searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            saveResume(r);
            LOGGER.info(r + " Successfully saved");
        } else {
            LOGGER.warning(uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
    }

    @Override
    public void update(Resume r) {
        LOGGER.info("Update " + r);
        String uuid = r.getUuid();
        T searchKey = getSearchKeyIfExist(uuid);
        updateResume(searchKey, r);
        LOGGER.info(r + " Successfully updated");
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
        T searchKey = getSearchKeyIfExist(uuid);
        deleteResume(searchKey);
        LOGGER.info(uuid + " Successfully deleted");
    }

    @Override
    public List<Resume> getAllSorted() {
        LOGGER.info("Get all sorted");
        List<Resume> resumesList = getAllResumesAsList();
        resumesList.sort(Resume::compareTo);
        return resumesList;
    }

    private T getSearchKeyIfExist(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            return searchKey;
        } else {
            LOGGER.warning(uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void saveResume(Resume resume);

    protected abstract void updateResume(T searchKey, Resume resume);

    protected abstract Resume getResume(T searchKey);

    protected abstract void deleteResume(T searchKey);

    protected abstract T getSearchKey(String uuid);

    protected abstract boolean isExist(T searchKey);

    protected abstract List<Resume> getAllResumesAsList();
}
