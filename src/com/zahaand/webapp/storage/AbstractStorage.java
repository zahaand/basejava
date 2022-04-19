package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.Resume;

import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<K> implements Storage {

    protected static final Logger LOGGER = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void save(Resume r) {
        LOGGER.info("Save " + r.getUuid());
        String uuid = r.getUuid();
        K resumeKey = getResumeKey(uuid);
        if (!isExist(resumeKey)) {
            saveResume(resumeKey, r);
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
        K resumeKey = getResumeKeyIfExist(uuid);
        updateResume(resumeKey, r);
        LOGGER.info("Successfully updated " + r.getUuid());
    }

    @Override
    public Resume get(String uuid) {
        LOGGER.info("Get " + uuid);
        if (getResumeKeyIfExist(uuid) != null) {
            return getResume(getResumeKey(uuid));
        }
        return null;
    }

    @Override
    public void delete(String uuid) {
        LOGGER.info("Delete " + uuid);
        K resumeKey = getResumeKeyIfExist(uuid);
        deleteResume(resumeKey);
        LOGGER.info("Successfully deleted " + uuid);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOGGER.info("Get all sorted");
        List<Resume> resumesList = getAllResumes();
        resumesList.sort(Resume::compareTo);
        return resumesList;
    }

    private K getResumeKeyIfExist(String uuid) {
        K resumeKey = getResumeKey(uuid);
        if (isExist(resumeKey)) {
            return resumeKey;
        } else {
            LOGGER.warning(uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void saveResume(K resumeKey, Resume resume);

    protected abstract void updateResume(K resumeKey, Resume resume);

    protected abstract Resume getResume(K resumeKey);

    protected abstract void deleteResume(K resumeKey);

    protected abstract K getResumeKey(String uuid);

    protected abstract boolean isExist(K resumeKey);

    protected abstract List<Resume> getAllResumes();
}
