package com.zahaand.webapp.storage;

import com.zahaand.webapp.storage.strategy.ObjectStreamStrategy;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIRECTORY.getAbsolutePath(), new ObjectStreamStrategy()));
    }
}