package com.zahaand.webapp.storage;

import com.zahaand.webapp.storage.strategy.ObjectStreamStrategy;

public class ObjectStreamPathStorage extends PathStorage {
    protected ObjectStreamPathStorage(String directory) {
        super(directory, new ObjectStreamStrategy());
    }

}
