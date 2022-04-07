package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.StorageException;
import com.zahaand.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final SerializationStrategy strategy;

    protected AbstractPathStorage(String directory, SerializationStrategy strategy) {
        Objects.requireNonNull(directory, "directory must not be null");
        Objects.requireNonNull(strategy, "strategy must not be null");
        this.directory = Paths.get(directory);
        this.strategy = strategy;
        if (!Files.isDirectory(this.directory)) {
            throw new IllegalArgumentException(directory + " is not directory");
        }
        if (!Files.isReadable(this.directory) || !Files.isWritable(this.directory)) {
            throw new IllegalArgumentException(directory + " is nor readable / writable");
        }
        LOGGER.info("Create new Path Storage");
    }

    @Override
    protected void saveResume(Path path, Resume resume) {
        LOGGER.info("Save " + resume.getUuid());
        try {
            Files.createFile(path);
        } catch (IOException e) {
            LOGGER.warning(resume + " do not save");
            throw new StorageException("Path create error", path.toString(), e);
        }
        updateResume(path, resume);
        LOGGER.info("Successfully saved " + resume.getUuid());
    }

    @Override
    protected void updateResume(Path path, Resume resume) {
        LOGGER.info("Update " + resume.getUuid());
        try {
            writeResume(resume, new BufferedOutputStream(new FileOutputStream(String.valueOf(path))));
        } catch (IOException e) {
            LOGGER.warning(resume + " do not update");
            throw new StorageException("IO error", path.toString(), e);
        }
        LOGGER.info("Successfully updated " + resume.getUuid());
    }

    @Override
    protected Resume getResume(Path path) {
        LOGGER.info("Get " + path);
        try {
            return readResume(new BufferedInputStream(new FileInputStream(String.valueOf(path))));
        } catch (IOException e) {
            LOGGER.warning(path + " do not get");
            throw new StorageException("Path read error", path.toString(), e);
        }
    }

    @Override
    protected void deleteResume(Path path) {
        LOGGER.info("Delete " + path);
        try {
            Files.delete(path);
        } catch (IOException e) {
            LOGGER.warning(path + " do not delete");
            throw new StorageException("Path delete error", path.toString(), e);
        }
        LOGGER.info("Successfully deleted " + path);
    }

    @Override
    protected Path getResumeKey(String uuid) {
        LOGGER.info("Get key " + uuid);
        return Paths.get(String.valueOf(directory), uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        LOGGER.info("Is exist? " + path);
        return Files.exists(path);
    }

    @Override
    protected List<Resume> getAllResumes() {
        LOGGER.info("Get all resumes");
        List<Resume> resumes = new ArrayList<>();
        try {
            Files.list(directory).forEach(x -> resumes.add(getResume(x)));
        } catch (IOException e) {
            LOGGER.warning("All resumes do not get");
            throw new StorageException("Get all resumes error", null, e);
        }
        LOGGER.info("Successfully gotten all resumes");
        return resumes;
    }

    @Override
    public int size() {
        LOGGER.info("Get size");
        try {
            return Files.list(directory).toArray().length;
        } catch (IOException e) {
            LOGGER.warning("Size do not get");
            throw new StorageException("Get size error", null, e);
        }
    }

    @Override
    public void clear() {
        LOGGER.info("Clear directory");
        try {
            Files.list(directory).forEach(this::deleteResume);
        } catch (IOException e) {
            LOGGER.warning("Directory do not clear");
            throw new StorageException("Path clear error", null, e);
        }
    }

    public void writeResume(Resume resume, OutputStream outputStream) throws IOException {
        strategy.writeResume(resume, outputStream);
    }

    public Resume readResume(InputStream inputStream) throws IOException {
        return strategy.readResume(inputStream);
    }
}
