package com.zahaand.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapResumeStorageTest.class,
        MapUuidStorageTest.class,
        FileStorageTest.class,
        PathStorageTest.class,
        XmlStorageTest.class,
        JsonStorageTest.class,
        DataStorageTest.class,
        SqlStorageTest.class
})
public class AllStorageTest {
}
