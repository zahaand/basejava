package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MapStorageTest {
    private final Map<String, Resume> storage = new HashMap<>();

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    @Before
    public void setUp() {
        storage.clear();
        storage.put(UUID_1, new Resume(UUID_1));
        storage.put(UUID_2, new Resume(UUID_2));
        storage.put(UUID_3, new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1);
        storage.replace(UUID_1, newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.replace(UUID_4, new Resume(UUID_4));
    }

    @Test
    public void save() {
        Resume newResume = new Resume(UUID_4);
        storage.put(UUID_4, newResume);
        assertEquals(4, storage.size());
        assertEquals(newResume, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.put(UUID_1, new Resume(UUID_1));
    }

    @Test
    public void get() {
        assertEquals(new Resume(UUID_1), storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void delete() {
        storage.remove(UUID_1);
        Resume[] storageTest = {new Resume(UUID_2), new Resume(UUID_3)};
        assertEquals(2, storage.size());
        assertArrayEquals(storageTest, storage.values().toArray(new Resume[0]));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.remove(UUID_4);
    }

    @Test
    public void getAll() {
        Resume[] storageTest = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        assertArrayEquals(storageTest, storage.values().toArray(new Resume[0]));
        assertEquals(3, storage.size());
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}