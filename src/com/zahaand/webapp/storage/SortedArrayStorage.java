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
        if (size > 1) {
            int index = Math.abs(getIndex(uuid));
            System.arraycopy(storage, index, storage, index + 1, size - index);
            System.out.println("ARRAY SORTED");
        }
    }
}

