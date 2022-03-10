package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void updateResume(Resume resume, Resume r) {
        storage.replace((resume).getUuid(), r);
    }

    @Override
    protected void saveResume(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(Resume resume) {
        return resume;
    }

    @Override
    protected void deleteResume(Resume resume) {
        storage.remove(resume.getUuid());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Resume uuid) {
        return uuid != null;
    }

    @Override
    public List<Resume> getAllResumesAsList() {
        return new ArrayList<>(storage.values());
    }
}
