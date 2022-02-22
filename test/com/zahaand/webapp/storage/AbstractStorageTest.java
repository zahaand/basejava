package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final String FULL_NAME_1 = "fullName1";
    private static final String FULL_NAME_2 = "fullName1";
    private static final String FULL_NAME_3 = "fullName1";
    private static final String FULL_NAME_4 = "fullName1";

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1, FULL_NAME_1));
        storage.save(new Resume(UUID_2, FULL_NAME_2));
        storage.save(new Resume(UUID_3, FULL_NAME_3));
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, FULL_NAME_1);
        storage.update(newResume);
        assertSame(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume(UUID_4));
    }

    @Test
    public void save() {
        Resume newResume = new Resume(UUID_4, FULL_NAME_4);
        storage.save(newResume);
        assertEquals(newResume, storage.get(UUID_4));
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_1, FULL_NAME_1));
    }

    @Test
    public void get() {
        assertEquals(new Resume(UUID_3, FULL_NAME_3), storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        Resume[] storageTest = {new Resume(UUID_2, FULL_NAME_2), new Resume(UUID_3, FULL_NAME_3)};
        assertArrayEquals(storageTest, storage.getAll());
        assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void getAll() {
        Resume[] storageTest = {new Resume(UUID_1, FULL_NAME_1), new Resume(UUID_2, FULL_NAME_2), new Resume(UUID_3, FULL_NAME_3)};
        assertArrayEquals(storageTest, storage.getAll());
        assertEquals(3, storage.getAll().length);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}