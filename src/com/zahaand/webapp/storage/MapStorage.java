package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void update(Resume r) {
        String key = r.getUuid();
        if (storage.containsKey(key)) {
            storage.remove(key);
            storage.put(key, r);
            System.out.println(key + " SUCCESSFULLY UPDATED");
        } else {
            throw new NotExistStorageException(key);
        }
    }

    @Override
    public void save(Resume r) {
        String key = r.getUuid();
        if (storage.containsKey(key)) {
            throw new ExistStorageException(key);
        }
        storage.put(r.getUuid(), r);
        System.out.println(key + " SUCCESSFULLY SAVED");
    }

    @Override
    public Resume get(String uuid) {
        if (storage.containsKey(uuid)) {
            return storage.get(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void delete(String uuid) {
        if (storage.containsKey(uuid)) {
            storage.remove(uuid);
            System.out.println(uuid + " SUCCESSFULLY DELETED");
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    protected void updateResume(Object searchKey, Resume resume) {

    }

    @Override
    protected void saveResume(Resume resume) {

    }

    @Override
    protected Resume getResume(Object searchKey) {
        return null;
    }

    @Override
    protected void deleteResume(Object searchKey) {

    }

    @Override
    protected Object searchKey(Object uuid) {
        return null;
    }

//    @Override
//    protected void updateResume(Object searchKey, Resume resume) {
//        storage.replace((String) searchKey, resume);
//    }
//
//    @Override
//    protected void saveResume(Resume resume) {
//        storage.put(resume.getUuid(), resume);
//    }
//
//    @Override
//    protected Resume getResume(Object searchKey) {
//        String key = (String) searchKey;
//        return storage.get(key);
//    }
//
//    @Override
//    protected void deleteResume(Object searchKey) {
//        storage.remove((String) searchKey);
//    }
//
//    @Override
//    protected Object searchKey(Object uuid) {
//        String key = (String) uuid;
//        if (storage.containsKey(key)) {
//            return key;
//        }
//        return -1;
//    }

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
