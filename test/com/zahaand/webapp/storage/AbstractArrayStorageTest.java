package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.exception.StorageException;
import com.zahaand.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AbstractArrayStorageTest {
    private final ArrayStorage storage = new ArrayStorage();

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid1";

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
        storage.update(new Resume(UUID_4));
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Assert.assertEquals(0, storage.getIndex("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
    }

    @Test(expected = StorageException.class)
    public void save() {
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExis() {
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
    }

    @Test
    public void getAll() {
        Assert.assertEquals(3, storage.getAll().length);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }
}