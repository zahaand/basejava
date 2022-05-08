package com.zahaand.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static Config configInstance;
    private static final File PROPERTIES = new File("config/resumes.properties");
    private final File storageDir;

    private Config() {
        try (InputStream inputStream = new FileInputStream(PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            storageDir = new File(properties.getProperty("storage.dir"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPERTIES.getAbsolutePath(), e);
        }
    }

    public static Config getInstance() {
        if (configInstance == null) {
            configInstance = new Config();
        }
        return configInstance;
    }

    public File getStorageDir() {
        return storageDir;
    }
}
