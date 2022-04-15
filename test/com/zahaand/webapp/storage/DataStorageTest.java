package com.zahaand.webapp.storage;

import com.zahaand.webapp.storage.serializer.DataStreamSerializer;

public class DataStorageTest extends AbstractStorageTest {
    public DataStorageTest() {
        super(new PathStorage(STORAGE_DIRECTORY.getAbsolutePath(), new DataStreamSerializer()));
    }
}
