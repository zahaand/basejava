package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateResume(Object index, Resume resume) {
        int i = Integer.parseInt(index.toString());
        storage.set(i, resume);
    }

    @Override
    protected void saveResume(Resume resume) {
        storage.add(resume);
    }

    @Override
    protected Resume getResume(Object index) {
        int i = Integer.parseInt(index.toString());
        return storage.get(i);
    }

    @Override
    protected void deleteResume(Object index) {
        int i = Integer.parseInt(index.toString());
        storage.remove(i);
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
    protected int getIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }
}
