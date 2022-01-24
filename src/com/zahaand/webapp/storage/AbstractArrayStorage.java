package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 4;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public final void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        if (index >= 0) {
            storage[index] = resume;
            System.out.println(uuid + " SUCCESSFULLY UPDATED");
        } else {
            System.out.println(uuid + " NOT FOUND");
        }
    }

    @Override
    public final void save(Resume resume) {
        if (size != storage.length){
            String uuid = resume.getUuid();
            if (getIndex(uuid) < 0) {
                saveResume(resume);
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
    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println(uuid + " NOT FOUND");
        return null;
    }

    @Override
    public final void delete(String uuid) {
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

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveResume(Resume resume);
}
