package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {
    private final Map<Resume, Resume> storage = new HashMap<>();

    @Override
    protected void updateResume(Object uuid, Resume resume) {
        storage.replace(resume, resume);
    }

    @Override
    protected void saveResume(Resume resume) {
        storage.put(resume, resume);
    }

    @Override
    protected Resume getResume(Object uuid) {
        Resume resume = new Resume((String) uuid, "dummy");
        return storage.get(resume);
    }

    @Override
    protected void deleteResume(Object uuid) {
        Resume resume = new Resume((String) uuid, "dummy");
        storage.remove(resume);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Resume resume = new Resume(uuid, "dummy");
        return storage.containsKey(resume) ? uuid : null;
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
    protected boolean isExist(Object resume) {
        return resume != null;
    }

    @Override
    public List<Resume> getAllResumesAsList() {
        Resume[] resumesArray = storage.values().toArray((new Resume[0]));
        List<Resume> resumesList = new ArrayList<>();
        Collections.addAll(resumesList, resumesArray);
        return resumesList;
    }
}
