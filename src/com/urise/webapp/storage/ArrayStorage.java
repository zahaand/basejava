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
        if (size < storage.length) {
            if (findIndex(resume.getUuid()) < 0) {
                storage[size] = resume;
                size++;
                System.out.println(resume.getUuid() + " SUCCESSFULLY SAVED");
            } else {
                System.out.println(resume.getUuid() + " ALREADY EXISTS");
            }
        } else {
            System.out.println("ARRAY IS FULL");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
                return storage[index];
        } else {
            System.out.println(uuid + " NOT FOUND");
        }
        return null;
    }

    public void delete(String uuid) {
        int index = findIndex((uuid));
        if (index >= 0) {
            size--;
            System.arraycopy(storage, size + 1, storage, size, size - index);
            storage[size] = null;
            System.out.println(uuid + " SUCCESSFULLY DELETED");
        } else {
            System.out.println(uuid + " NOT FOUND");
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
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            System.out.println(resume.getUuid() + " SUCCESSFULLY UPDATED");
        } else {
            System.out.println("ID NOT FOUND");
        }
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
