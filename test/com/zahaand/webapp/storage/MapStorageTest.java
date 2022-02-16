package com.zahaand.webapp.storage;

public class MapStorageTest extends AbstractStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }
//
//    @Override
//    @Test
//    public void getAll() {
//        assertEquals(3, storage.getAll().length);
//    }
//
//    @Override
//    @Test
//    public void delete() {
//        storage.delete("uuid1");
//        assertEquals(2, storage.size());
//    }
}