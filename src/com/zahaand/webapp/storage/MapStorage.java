package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void updateResume(Object index, Resume resume) {
        storage.replace(String.valueOf(index), resume);
    }

    @Override
    protected void saveResume(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Object index) {
        return storage.get(String.valueOf(index));
    }

    @Override
    protected void deleteResume(Object index) {
        storage.remove(String.valueOf(index));
    }

    @Override
    protected int getIndex(String uuid) {
        if (storage.containsKey(uuid)) {
            return uuid.hashCode();
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
