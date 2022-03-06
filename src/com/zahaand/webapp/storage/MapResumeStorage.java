package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void updateResume(Object resume, Resume r) {
        storage.replace(((Resume) resume).getUuid(), r);
    }

    @Override
    protected void saveResume(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(Object resume) {
        return (Resume) resume;
    }

    @Override
    protected void deleteResume(Object resume) {
        storage.remove(((Resume) resume).getUuid());
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
    protected boolean isExist(Object uuid) {
        return uuid != null;
    }

    @Override
    public List<Resume> getAllResumesAsList() {
        Resume[] resumesArray = storage.values().toArray((new Resume[0]));
        List<Resume> resumesList = new ArrayList<>();
        Collections.addAll(resumesList, resumesArray);
        return resumesList;
    }
}
