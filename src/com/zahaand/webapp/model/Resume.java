package com.zahaand.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * initial resume class
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    private String uuid;
    private String fullName;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);
    private static final long SERIALIZABLE_VERSION = 1L;

    public Resume() {
    }

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

    public String getFullName() {
        return fullName;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public void addContact(ContactType contactType, String contact) {
        contacts.put(contactType, contact);
    }

    public void addSectionData(SectionType sectionType, AbstractSection section) {
        sections.put(sectionType, section);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(uuid).append(" (").append(fullName).append(")").append("\n");
        for (ContactType contact : ContactType.values()) {
            stringBuilder.append(contact.getTitle()).append(": ").append(contacts.get(contact)).append("\n");
        }
        stringBuilder.append("\n");
        for (SectionType section : SectionType.values()) {
            stringBuilder.append(section.getTitle()).append(": \n").append(sections.get(section)).append("\n").append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid) && fullName.equals(resume.fullName) && Objects.equals(contacts, resume.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts);
    }

    @Override
    public int compareTo(Resume o) {
        int uuidComparator = uuid.compareTo(o.uuid);
        int fullNameComparator = fullName.compareTo(o.fullName);
        return (fullNameComparator != 0 ? fullNameComparator : uuidComparator);
    }
}
