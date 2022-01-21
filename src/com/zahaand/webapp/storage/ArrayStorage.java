package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        if (size < storage.length - 1) {
            String uuid = resume.getUuid();
            if (getIndex(uuid) < 0) {
                storage[size] = resume;
                size++;
                System.out.println(uuid + " SUCCESSFULLY SAVED");
            } else {
                System.out.println(uuid + " ALREADY EXISTS");
            }
        } else {
            System.out.println("ARRAY IS OVERFLOW");
        }
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
