package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

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
    protected Resume getResume(Object getSearchKey) {
        String key = (String) getSearchKey;
        return storage.get(key);
    }

    @Override
    protected void deleteResume(Object getSearchKey) {
        storage.remove((String) getSearchKey);
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
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Object getSearchKey) {
        return getSearchKey((String) getSearchKey) != null;
    }
}
