package com.zahaand.webapp.storage;

import java.io.File;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new ObjectStreamStorage(new File(STORAGE_DIRECTORY)));
    }
}