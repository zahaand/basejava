package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.StorageException;
import com.zahaand.webapp.model.Resume;
import com.zahaand.webapp.storage.serializer.SerializationStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;
    private final SerializationStrategy strategy;

    protected FileStorage(File directory, SerializationStrategy strategy) {
        Objects.requireNonNull(directory, "directory must not be null");
        Objects.requireNonNull(strategy, "strategy must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getPath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getPath() + " is nor readable / writable");
        }
        this.directory = directory;
        this.strategy = strategy;
        LOGGER.info("Create new File Storage");
    }

    @Override
    protected void saveResume(File file, Resume resume) {
        LOGGER.info("Save " + resume.getUuid());
        try {
            file.createNewFile();
        } catch (IOException e) {
            LOGGER.warning(resume + " do not save");
            throw new StorageException("File create error", file.getName(), e);
        }
        updateResume(file, resume);
        LOGGER.info("Successfully saved " + resume.getUuid());
    }

    @Override
    protected void updateResume(File file, Resume resume) {
        LOGGER.info("Update " + resume.getUuid());
        try {
            strategy.writeResume(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            LOGGER.warning(resume + " do not update");
            throw new StorageException("IO error", file.getName(), e);
        }
        LOGGER.info("Successfully updated " + resume.getUuid());
    }

    @Override
    protected Resume getResume(File file) {
        LOGGER.info("Get " + file);
        try {
            return strategy.readResume(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            LOGGER.warning(file + " do not get");
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected void deleteResume(File file) {
        LOGGER.info("Delete " + file);
        if (!file.delete()) {
            LOGGER.warning(file + " do not delete");
            throw new StorageException("File delete error", file.getName());
        }
        LOGGER.info("Successfully deleted " + file);
    }

    @Override
    protected File getResumeKey(String uuid) {
        LOGGER.info("Get key " + uuid);
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        LOGGER.info("Is exist? " + file);
        return file.exists();
    }

    @Override
    protected List<Resume> getAllResumes() {
        LOGGER.info("Get all resumes");
        List<Resume> resumes = new ArrayList<>();
        for (File file : getAllFilesArray()) {
            resumes.add(getResume(file));
        }
        LOGGER.info("Successfully got all resumes");
        return resumes;
    }

    @Override
    public int size() {
        LOGGER.info("Get size");
        return getAllFilesArray().length;
    }

    @Override
    public void clear() {
        LOGGER.info("Clear directory");
        for (File file : getAllFilesArray()) {
            deleteResume(file);
        }
        LOGGER.info("Successfully cleared");
    }

    private File[] getAllFilesArray() {
        File[] files = directory.listFiles();
        if (files != null) {
            return files;
        } else {
            throw new StorageException("Directory is empty ", directory.getName());
        }
    }
}
