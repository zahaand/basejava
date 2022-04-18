package com.zahaand.webapp.storage;

import com.zahaand.webapp.storage.serializer.XmlStreamSerializer;

public class XmlStorageTest extends AbstractStorageTest {
    public XmlStorageTest() {
        super(new PathStorage(STORAGE_DIRECTORY.getAbsolutePath(), new XmlStreamSerializer()));
    }
}