package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            System.out.println(resume.getUuid() + " SUCCESSFULLY UPDATED");
        } else {
            System.out.println("ID NOT FOUND");
        }
    }

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

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
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
