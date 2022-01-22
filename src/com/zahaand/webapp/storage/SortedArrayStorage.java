package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, uuid);
    }

    @Override
    protected void sortStorage(String uuid) {
        int index = Math.abs(Arrays.binarySearch(storage, uuid));
        if (storage[index].getUuid().compareTo(uuid) > 0) {
            System.arraycopy(storage, index, storage, index + 1, size - index);
        }
    }
}

