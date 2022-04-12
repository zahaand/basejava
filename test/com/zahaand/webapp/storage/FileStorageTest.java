package com.zahaand.webapp.storage;

import com.zahaand.webapp.storage.serializer.ObjectStreamStrategy;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIRECTORY, new ObjectStreamStrategy()));
    }
}