package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        if (size < storage.length - 1) {
            String uuid = resume.getUuid();
            if (getIndex(uuid) < 0) {
                if (size == 0) {
                    storage[size] = resume;
                } else {
                    for (int i = 0; i < storage.length; i++) {
                        if (storage[i].compareTo(resume) > 0) {
                            System.arraycopy(storage, i, storage, i + 1, (size - 1) - i);
                            storage[i] = resume;
                        } else {
                            System.out.println(uuid + " ALREADY EXISTS");
                        }
                    }
                    size++;
                    System.out.println(uuid + " SUCCESSFULLY SAVED");
                }
            }
        } else {
            System.out.println("ARRAY IS OVERFLOW");
        }
    }

    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, uuid);
    }
}

