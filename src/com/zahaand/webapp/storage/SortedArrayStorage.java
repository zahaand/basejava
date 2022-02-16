package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void insertElement(Resume resume) {
        int index = Math.abs((Integer) searchKey(resume.getUuid())) - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }

    @Override
    protected Object searchKey(Object uuid) {
        Resume resume = new Resume(String.valueOf(uuid));
        return Arrays.binarySearch(storage, 0, size, resume);
    }
}

