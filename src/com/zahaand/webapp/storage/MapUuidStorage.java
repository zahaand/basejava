package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return storage.containsKey(uuid) ? uuid : null;
    }

    @Override
    protected void updateResume(String uuid, Resume resume) {
        storage.replace(uuid, resume);
    }

    @Override
    protected void saveResume(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void deleteResume(String uuid) {
        storage.remove(uuid);
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
    protected boolean isExist(String uuid) {
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
