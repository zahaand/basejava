package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void updateResume(Object uuid, Resume resume) {
        storage.replace((String) uuid, resume);
    }

    @Override
    protected void saveResume(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Object uuid) {
        return storage.get((String) uuid);
    }

    @Override
    protected void deleteResume(Object uuid) {
        storage.remove((String) uuid);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.containsKey(uuid) ? uuid : null;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> sortStorage() {
        List<Resume> resumes = (List<Resume>) storage.values();
        resumes.sort(Resume::compareTo);
        return resumes;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Object uuid) {
        return uuid != null;
    }
}
