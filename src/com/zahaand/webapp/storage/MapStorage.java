package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void updateResume(int index, Resume resume) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected void saveResume(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(int index) {
        String[] keys = storage.keySet().toArray(new String[0]);
        return storage.get(keys[index]);
    }

    @Override
    protected void deleteResume(int index) {
        String[] keys = storage.keySet().toArray(new String[0]);
        storage.remove(keys[index]);
    }

    @Override
    protected int getIndex(String uuid) {
        String[] keys = storage.keySet().toArray(new String[0]);
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals(uuid)) {
                return i;
            }
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
