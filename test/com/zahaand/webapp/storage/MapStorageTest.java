package com.zahaand.webapp.storage;

import org.junit.Ignore;

public class MapStorageTest extends AbstractStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    @Ignore
    public void saveException() {
        super.saveException();
    }
}