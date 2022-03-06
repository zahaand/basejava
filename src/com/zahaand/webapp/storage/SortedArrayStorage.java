package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage implements Comparator<Resume> {

    @Override
    protected Object getSearchKey(String uuid) {
        Resume resume = new Resume(uuid, "dummy");
        return Arrays.binarySearch(storage, 0, size, resume, Comparator.comparing(Resume::getUuid));
    }

    @Override
    protected void insertElement(Resume resume) {
        String uuid = resume.getUuid();
        int index = -(int) getSearchKey(uuid) - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }

    @Override
    public int compare(Resume o1, Resume o2) {
        return o1.getUuid().compareTo(o2.getUuid());
    }
}

