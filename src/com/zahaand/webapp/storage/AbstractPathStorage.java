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

    protected AbstractPathStorage(String directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        this.directory = Paths.get(directory);
        if (!Files.isDirectory(this.directory)) {
            throw new IllegalArgumentException(directory + " is not directory");
        }
        if (!Files.isReadable(this.directory) || !Files.isWritable(this.directory)) {
            throw new IllegalArgumentException(directory + " is nor readable / writable");
        }
    }

    @Override
    protected void saveResume(Path path, Resume resume) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Path create error", path.toString(), e);
        }
        updateResume(path, resume);
    }

    @Override
    protected void updateResume(Path path, Resume resume) {
        try {
            writeResume(resume, new BufferedOutputStream(new FileOutputStream(String.valueOf(path))));
        } catch (IOException e) {
            throw new StorageException("IO error", path.toString(), e);
        }
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return readResume(new BufferedInputStream(new FileInputStream(String.valueOf(path))));
        } catch (IOException e) {
            throw new StorageException("Path read error", path.toString(), e);
        }
    }

    @Override
    protected void deleteResume(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", path.toString(), e);
        }
    }

    @Override
    protected Path getResumeKey(String uuid) {
        return Paths.get(String.valueOf(directory), uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected List<Resume> getAllResumes() {
        List<Resume> resumes = new ArrayList<>();
        try {
            Files.list(directory).forEach(x -> resumes.add(getResume(x)));
        } catch (IOException e) {
            throw new StorageException("Get all resumes error", null, e);
        }
        return resumes;
    }

    @Override
    public int size() {
        try {
            return Files.list(directory).toArray().length;
        } catch (IOException e) {
            throw new StorageException("Get size error", null, e);
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::deleteResume);
        } catch (IOException e) {
            throw new StorageException("Path clear error", null, e);
        }
    }

    protected abstract void writeResume(Resume resume, OutputStream outputStream) throws IOException;

    protected abstract Resume readResume(InputStream inputStream) throws IOException;
}
