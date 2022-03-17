package com.zahaand.webapp.model;

import java.util.List;
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
    private Map<ResumeContacts, String> contacts;
    private Map<ResumeSections, AbstractSection> sectionsData;
    private List<TimeIntervalSection> experience;
    private List<TimeIntervalSection> education;

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

    public void setContact(ResumeContacts contact, String data) {
        contacts.put(contact, data);
    }

    public void setSectionData(ResumeSections section, AbstractSection data) {
        sectionsData.put(section, data);
    }

    public void setExperience(List<TimeIntervalSection> experience) {
        this.experience = experience;
    }

    public void setEducation(List<TimeIntervalSection> education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return uuid + '(' + fullName + ')';
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
