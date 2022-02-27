package com.zahaand.webapp.storage;

import com.zahaand.webapp.model.Resume;

import java.util.List;

public interface Storage {

    void save(Resume r);

    void update(Resume r);

    Resume get(String uuid);

//    Resume[] getAll();

    List<Resume> getAllSorted();

    int size();

    void delete(String uuid);

    void clear();
}
