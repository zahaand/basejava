package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.Resume;

import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<ResumeKeyType> implements Storage {

    protected static final Logger LOGGER = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void save(Resume r) {
        LOGGER.info("Save " + r.getUuid());
        String uuid = r.getUuid();
        ResumeKeyType resumeKey = getResumeKey(uuid);
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
        ResumeKeyType resumeKey = getResumeKeyIfExist(uuid);
        updateResume(resumeKey, r);
        LOGGER.info("Successfully updated " + r.getUuid());
    }

    @Override
    public Resume get(String uuid) {
        LOGGER.info("GET " + uuid);
        if (getResumeKeyIfExist(uuid) != null) {
            return getResume(getResumeKey(uuid));
        }
        return null;
    }

    @Override
    public void delete(String uuid) {
        LOGGER.info("Delete " + uuid);
        ResumeKeyType resumeKey = getResumeKeyIfExist(uuid);
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

    private ResumeKeyType getResumeKeyIfExist(String uuid) {
        ResumeKeyType resumeKey = getResumeKey(uuid);
        if (isExist(resumeKey)) {
            return resumeKey;
        } else {
            LOGGER.warning(uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void saveResume(ResumeKeyType resumeKey, Resume resume);

    protected abstract void updateResume(ResumeKeyType resumeKey, Resume resume);

    protected abstract Resume getResume(ResumeKeyType resumeKey);

    protected abstract void deleteResume(ResumeKeyType resumeKey);

    protected abstract ResumeKeyType getResumeKey(String uuid);

    protected abstract boolean isExist(ResumeKeyType resumeKey);

    protected abstract List<Resume> getAllResumes();
}
