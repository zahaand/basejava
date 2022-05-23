package com.zahaand.webapp.storage;

import com.zahaand.webapp.Config;
import com.zahaand.webapp.MainTestResumeData;
import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected final Storage storage;
    protected static final File STORAGE_DIRECTORY = Config.getInstance().getStorageDir();

    private final static Resume RESUME_1 = MainTestResumeData.createResume("uuid1", "FullName1");
    private final static Resume RESUME_2 = MainTestResumeData.createResume("uuid2", "FullName2");
    private final static Resume RESUME_3 = MainTestResumeData.createResume("uuid3", "FullName3");
    private final static Resume RESUME_4 = MainTestResumeData.createResume("uuid4", "FullName4");

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(RESUME_1);
        assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertEquals(RESUME_4, storage.get(RESUME_4.getUuid()));
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void get() {
        assertEquals(RESUME_3, storage.get(RESUME_3.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(RESUME_4.getUuid());
    }

    @Test
    public void delete() {
        storage.delete(RESUME_1.getUuid());
        List<Resume> storageTest = new ArrayList<>();
        storageTest.add(RESUME_2);
        storageTest.add(RESUME_3);
        assertEquals(storageTest, storage.getAllSorted());
        assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(RESUME_4.getUuid());
    }

    @Test
    public void getAllSorted() {
        List<Resume> storageTest = new ArrayList<>();
        storageTest.add(RESUME_1);
        storageTest.add(RESUME_2);
        storageTest.add(RESUME_3);
        assertEquals(storageTest, storage.getAllSorted());
        assertEquals(3, storage.size());
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}