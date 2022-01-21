package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            System.out.println(resume.getUuid() + " SUCCESSFULLY UPDATED");
        } else {
            System.out.println("ID NOT FOUND");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println(uuid + " NOT FOUND");
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex((uuid));
        if (index >= 0) {
            size--;
            System.arraycopy(storage, size + 1, storage, size, size - index);
            storage[size] = null;
            System.out.println(uuid + " SUCCESSFULLY DELETED");
        } else {
            System.out.println(uuid + " NOT FOUND");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);
}
