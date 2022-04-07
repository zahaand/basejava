package com.zahaand.webapp.storage;

import com.zahaand.webapp.storage.strategy.ObjectStreamStrategy;

import java.io.File;

public class ObjectStreamStorage extends FileStorage {
    protected ObjectStreamStorage(File directory) {
        super(directory, new ObjectStreamStrategy());
    }
}
