package com.zahaand.webapp.storage;

public class ObjectStreamPathStorage extends AbstractPathStorage {
    protected ObjectStreamPathStorage(String directory) {
        super(directory, new ObjectStreamStrategy());
    }

}
