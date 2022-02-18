package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateResume(Object searchKey, Resume resume) {
        int index = (int) searchKey;
        storage.set(index, resume);
    }

    @Override
    protected void saveResume(Resume resume) {
        storage.add(resume);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        int index = (int) searchKey;
        return storage.get(index);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        int index = (int) searchKey;
        storage.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object getKey(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    protected boolean checkKey(Object key) {
        return (int) key >= 0;
    }
}
