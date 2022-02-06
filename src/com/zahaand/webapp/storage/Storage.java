package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

public interface Storage {

    void clear();

    void update(Resume r);

    void save(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    Object[] getAll();

    int size();
}
