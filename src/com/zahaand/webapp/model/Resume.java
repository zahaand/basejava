package com.zahaand.webapp.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * initial resume class
 */
public class Resume implements Comparable<Resume> {

    // unique identifier
    private final String uuid;
    private final String fullName;
    private Map<ContactType, String> contacts = new HashMap<>();
    private Map<SectionType, AbstractSection> sectionsData = new HashMap<>();

    // random UUID generation
    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName(String uuid) {
        return fullName;
    }

    public void setContact(ContactType contact, String data) {
        contacts.put(contact, data);
    }

    public void setSectionData(SectionType section, AbstractSection data) {
        sectionsData.put(section, data);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(uuid).append(" (").append(fullName).append(")").append("\n");
        for (ContactType key : ContactType.values()) {
            stringBuilder.append(key.getTitle()).append(": ").append(contacts.get(key)).append("\n");
        }
        stringBuilder.append("\n");
        for (SectionType key : SectionType.values()) {
            stringBuilder.append(key.getTitle()).append(": \n").append(sectionsData.get(key)).append("\n").append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid) && fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

    @Override
    public int compareTo(Resume o) {
        int uuidComparator = uuid.compareTo(o.uuid);
        int fullNameComparator = fullName.compareTo(o.fullName);
        return (fullNameComparator != 0 ? fullNameComparator : uuidComparator);
    }
}
