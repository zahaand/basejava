package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size != 9999 && !checkResume(resume)) {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        if (checkUuid(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i] != null) {
                    if (storage[i].toString().equals(uuid)) {
                        return storage[i];
                    }
                }
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (checkUuid(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].toString().equals(uuid)) {
                    size--;
                    System.arraycopy(storage, i + 1, storage, i, size - i);
                    storage[size] = null;
                    break;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        if (checkResume(resume)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(resume.getUuid())) {
                    storage[i] = resume;
                }
            }
        }
    }
    
    private boolean checkResume(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid())) {
                return true;
            } else {
                System.out.println("NOT FOUND");
            }
        }
        return false;
    }

    private boolean checkUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return true;
            } else {
                System.out.println("NOT FOUND");
            }
        }
        return false;
    }
}
