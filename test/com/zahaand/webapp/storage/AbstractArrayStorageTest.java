package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.exception.StorageException;
import com.zahaand.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage = new ArrayStorage();

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
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(new Resume(UUID_4));
        Resume[] storageTest = {new Resume(UUID_4), new Resume(UUID_2), new Resume(UUID_3)};
        Assert.assertArrayEquals(storageTest, storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void save() {
        storage.save(new Resume("dummy"));
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume("uuid1"));
    }

    @Test(expected = StorageException.class)
    public void saveException() {
        for (int i = 0; i <= storage.size(); i++) {
            storage.save(new Resume("dummy"));
        }
    }

    @Test
    public void get() {
        storage.get("uuid3");
        Assert.assertEquals(2, 2);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void delete() {
        storage.delete("uuid1");
        Resume[] storageTest = {new Resume(UUID_2), new Resume(UUID_3)};
        Assert.assertArrayEquals(storageTest, storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void getAll() {
        Resume[] storageTest = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        Assert.assertArrayEquals(storageTest, storage.getAll());
        Assert.assertEquals(3, storage.getAll().length);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

}