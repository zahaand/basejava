package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

public class ListStorage extends AbstractStorage {
    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        int index = listStorage.indexOf(resume);
        if (index >= 0) {
            return index;
        } else {
            return -1;
        }
    }
}
