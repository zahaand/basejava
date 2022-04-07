package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.StorageException;
import com.zahaand.webapp.model.Resume;
import com.zahaand.webapp.storage.strategy.SerializationStrategy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final SerializationStrategy strategy;

    protected PathStorage(String directory, SerializationStrategy strategy) {
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
            strategy.writeResume(resume, new BufferedOutputStream(Files.newOutputStream(path)));
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
            return strategy.readResume(new BufferedInputStream(Files.newInputStream(path)));
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
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        LOGGER.info("Is exist? " + path);
        return Files.exists(path);
    }

    @Override
    protected List<Resume> getAllResumes() {
        LOGGER.info("Get all resumes");
        return getAllFiles().map(this::getResume).collect(Collectors.toList());
    }

    @Override
    public int size() {
        LOGGER.info("Get size");
        return getAllFiles().toArray().length;
    }

    @Override
    public void clear() {
        LOGGER.info("Clear directory");
        getAllFiles().forEach(this::deleteResume);
        LOGGER.info("Successfully cleared " + directory);
    }

    private Stream<Path> getAllFiles() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Get all files Stream error ", directory.toString());
        }
    }
}
