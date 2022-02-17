package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

public interface Storage {

    void save(Resume r);

    void update(Resume r);

    Resume get(String uuid);

    Resume[] getAll();

    int size();

    void delete(String uuid);

    void clear();
}
