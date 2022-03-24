package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage implements Comparator<Resume> {

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume resume = new Resume(uuid, "dummy");
        return Arrays.binarySearch(storage, 0, size, resume, Comparator.comparing(Resume::getUuid));
    }

    @Override
    protected void insertElement(int index, Resume resume) {
        int insertIndex = -index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - index);
        storage[index] = resume;
    }

    @Override
    public int compare(Resume o1, Resume o2) {
        return o1.getUuid().compareTo(o2.getUuid());
    }
}

