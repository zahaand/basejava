package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
                Resume resume = new Resume(uuid);
                return Arrays.binarySearch(storage, 0, size, resume);
    }

    @Override
    protected void saveResume(Resume resume) {
        if (size > 0) {
            int index = Math.abs(getIndex(resume.getUuid()));
            if (index < 0) {
                index--;
            }
            System.arraycopy(storage, index, storage, index + 1, size - index);
        }
    }
}

