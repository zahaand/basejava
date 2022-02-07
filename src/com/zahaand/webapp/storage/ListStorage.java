package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
        System.out.println("SUCCESSFULLY CLEARED");
    }

    @Override
    public void update(Resume r) {
        for (Resume resume : storage) {
            if (resume.equals(r)) {
                storage.set(getIndex(resume.getUuid()), r);
                System.out.println(r.getUuid() + " SUCCESSFULLY UPDATED");
                break;
            }
            throw new NotExistStorageException(r.getUuid());
        }
    }

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        if (storage.contains(r)) {
            throw new ExistStorageException(uuid);
        } else {
            storage.add(r);
            System.out.println(uuid + " SUCCESSFULLY SAVED");
        }
    }

    @Override
    public Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume.toString().equals(uuid)) {
                return resume;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        for (Resume resume : storage) {
            if (resume.toString().equals(uuid)) {
                storage.remove(getIndex(uuid));
                break;
            }
            throw new NotExistStorageException(uuid);
        }
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
        Resume resume = new Resume(uuid);
        return storage.indexOf(resume);
    }
}
