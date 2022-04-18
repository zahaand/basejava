package com.zahaand.webapp.storage;

import com.zahaand.webapp.storage.serializer.JsonStreamSerializer;

public class JsonStorageTest extends AbstractStorageTest {
    public JsonStorageTest() {
        super(new PathStorage(STORAGE_DIRECTORY.getAbsolutePath(), new JsonStreamSerializer()));
    }
}
