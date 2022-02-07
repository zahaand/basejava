package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
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
        storage.set(getIndex(r.getUuid()), r);


//        String uuid = r.getUuid();
//        while (listIterator.hasNext()) {
//            if (listIterator.next().equals(r)) {
//                listIterator.set(r);
//                System.out.println(uuid + " SUCCESSFULLY UPDATED");
//            } else {
//                throw new NotExistStorageException(uuid);
//            }
//        }
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
        return storage.get(getIndex(uuid));

//        while (listIterator.hasNext()) {
//            if (listIterator.next().toString().equals(uuid)) {
//                return listIterator.next();
//            }
//        }
//        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        storage.remove(getIndex(uuid));

//        while (listIterator.hasNext()) {
//            if (listIterator.next().toString().equals(uuid)) {
//                listIterator.remove();
//                break;
//            }
//        }
//            throw new NotExistStorageException(uuid);
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

    @Override
    protected void insertElement(Resume resume) {
        storage.add(resume);
    }
}
