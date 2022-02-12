package com.zahaand.webapp.storage;

import org.junit.Ignore;

public class ListStorageTest extends AbstractStorageTest{
    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    @Ignore
    public void saveException() {
        super.saveException();
    }
}