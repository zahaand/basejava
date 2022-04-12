package com.zahaand.webapp.storage;

import com.zahaand.webapp.storage.serializer.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIRECTORY.getAbsolutePath(), new XmlStreamSerializer()));
    }
}