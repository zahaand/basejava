package com.zahaand.webapp;

import com.zahaand.webapp.model.Resume;
import com.zahaand.webapp.storage.SortedArrayStorage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestSortedArrayStorage {
    static final SortedArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("fullName1");
        Resume r2 = new Resume("fullName2");
        Resume r3 = new Resume("fullName3");
        Resume r4 = new Resume("fullName4");

        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r1);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.update(r4);
        printAll();
        System.out.println("NEW SIZE = " + ARRAY_STORAGE.size());
        ARRAY_STORAGE.delete(r3.getUuid());
        System.out.println("NEW SIZE = " + ARRAY_STORAGE.size());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
