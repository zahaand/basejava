package com.zahaand.webapp.storage;

import com.zahaand.webapp.Config;

public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(Config.getInstance().getSqlStorage());
    }
}