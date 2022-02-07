package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();
    Iterator<Resume> iterator = storage.iterator();

    @Override
    public void clear() {
        storage.clear();
        System.out.println("SUCCESSFULLY CLEARED");
    }

    @Override
    public void update(Resume r) {
        while (iterator.hasNext()) {
            if (iterator.next().equals(r)) {
                iterator.remove();
                storage.add(r);
            } else {
                throw new NotExistStorageException(r.getUuid());
            }
        }
    }

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        if (storage.contains(r)) {
            throw new ExistStorageException(uuid);
        } else {
            storage.add(r);
        }
    }

    @Override
    public Resume get(String uuid) {
        while (iterator.hasNext()) {
            if (iterator.next().toString().equals(uuid)) {
                return iterator.next();
            } else {
                throw new NotExistStorageException(uuid);
            }
        }
        return null;
    }

    @Override
    public void delete(String uuid) {
        while (iterator.hasNext()) {
            if (iterator.next().toString().equals(uuid)) {
                iterator.remove();
            } else {
                throw new NotExistStorageException(uuid);
            }
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
}
