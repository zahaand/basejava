package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayStorageTest {
    private final ArrayStorage storage = new ArrayStorage();

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void getIndex() {
        Assert.assertEquals(0, storage.getIndex("uuid1"));
    }

    @Test
    public void insertElement() {
        Assert.assertNotNull(storage.get("uuid1"));
        Assert.assertNotNull(storage.get("uuid2"));
        Assert.assertNotNull(storage.get("uuid3"));
    }
}