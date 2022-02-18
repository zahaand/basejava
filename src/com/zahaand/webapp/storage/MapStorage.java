package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void updateResume(Object getKey, Resume resume) {
        storage.replace((String) getKey, resume);
    }

    @Override
    protected void saveResume(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Object getKey) {
        String key = (String) getKey;
        return storage.get(key);
    }

    @Override
    protected void deleteResume(Object getKey) {
        storage.remove((String) getKey);
    }

    @Override
    protected Object getKey(String uuid) {
        if (storage.containsKey(uuid)) {
            return uuid;
        }
        return null;
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
    protected boolean checkKey(Object key) {
        return storage.containsKey((String) key);
    }
}
