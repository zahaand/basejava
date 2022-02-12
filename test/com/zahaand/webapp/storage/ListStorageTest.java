package com.zahaand.webapp.storage;

import org.junit.Ignore;
import org.junit.Test;

public class ListStorageTest extends AbstractStorageTest{
    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    @Ignore
    @Test
    public void saveException() {
        super.saveException();
    }
}