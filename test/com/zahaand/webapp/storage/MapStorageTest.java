package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.Resume;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapStorageTest extends AbstractStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    @Test
    public void getAll() {
        assertEquals(new Resume("uuid1"), storage.get("uuid1"));
        assertEquals(new Resume("uuid2"), storage.get("uuid2"));
        assertEquals(new Resume("uuid3"), storage.get("uuid3"));
        assertEquals(3, storage.getAll().length);
    }

    @Override
    @Test
    public void delete() {
        storage.delete("uuid1");
        assertEquals(2, storage.size());
    }

    @Override
    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("uuid1");
        storage.get("uuid1");
    }
}