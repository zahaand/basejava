package com.zahaand.webapp.storage;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new ObjectStreamPathStorage(STORAGE_DIRECTORY));
    }
}