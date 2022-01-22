package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        int index = Arrays.binarySearch(storage, uuid);
        if (index >= 0) {
            System.arraycopy(storage, index, storage, index + 1, (size - 1) - index);
        }
        return index;
    }
}

