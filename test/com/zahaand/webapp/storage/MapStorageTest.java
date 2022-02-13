package com.zahaand.webapp.storage;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapStorageTest extends AbstractStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    @Test
    public void getAll() {
        assertEquals(3, storage.getAll().length);
    }

    @Override
    @Test
    public void delete() {
        assertEquals(2, storage.size());
    }

}