package com.zahaand.webapp.model;

import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;

/**
 * initial resume class
 */
public class Resume implements Comparator<Resume> {

    // unique identifier
    private final String uuid;
    private String fullName;


    // random UUID generation
    public Resume() {
       this (UUID.randomUUID().toString(), null);
    }

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compare(Resume resume1, Resume resume2) {
        String resume1FullName = resume1.getFullName();
        String resume2FullName = resume2.getFullName();
        if (resume1FullName.equals(resume2FullName)) {
            return resume1.getUuid().compareTo(resume2.getUuid());
        }
        return resume1.getFullName().compareTo(resume2.getFullName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
