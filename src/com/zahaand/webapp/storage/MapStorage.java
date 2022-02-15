package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void updateResume(Object searchKey, Resume resume) {
        storage.replace(String.valueOf(searchKey), resume);
    }

    @Override
    protected void saveResume(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        String key = String.valueOf(searchKey);
        return storage.get(key);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storage.remove(String.valueOf(searchKey));
    }

    @Override
    protected int searchKey(Object uuid) {
        String key = String.valueOf(uuid);
        if (storage.containsKey(key)) {
            return key.hashCode();
        }
        return -1;
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
}
