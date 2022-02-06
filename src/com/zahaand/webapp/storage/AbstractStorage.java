package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    protected List<Resume> listStorage = new ArrayList<>();

    @Override
    public void clear() {
        listStorage.clear();
        System.out.println("SUCCESSFULLY CLEARED");
    }

    @Override
    public void update(Resume r) {
        String uuid = r.getUuid();
        int index = getIndex(uuid);
        if (index > 0) {
            listStorage.set(index, r);
            System.out.println(uuid + " SUCCESSFULLY UPDATED");
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        int index = getIndex(uuid);
        if (index < 0) {
            listStorage.add(r);
            System.out.println(uuid + " SUCCESSFULLY SAVED");
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return listStorage.get(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            listStorage.remove(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public Resume[] getAll() {
        return listStorage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    protected abstract int getIndex(String uuid);
}
