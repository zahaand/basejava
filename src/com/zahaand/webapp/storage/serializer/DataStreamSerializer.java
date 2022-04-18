package com.zahaand.webapp.storage.serializer;

import com.zahaand.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void writeResume(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.writeUTF(resume.getUuid());
            dataOutputStream.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();
            dataOutputStream.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dataOutputStream.writeUTF(entry.getKey().name());
                dataOutputStream.writeUTF(entry.getValue());
            }

            Map<SectionType, AbstractSection> sections = resume.getSections();
            dataOutputStream.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                dataOutputStream.writeUTF(entry.getKey().name());
                switch (entry.getKey()) {
                    case OBJECTIVE, PERSONAL -> dataOutputStream.writeUTF(String.valueOf(entry.getValue()));
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        ListSection listSection = (ListSection) entry.getValue();
                        List<String> bulletedList = listSection.getBulletedList();
                        dataOutputStream.writeInt(bulletedList.size());
                        for (String string : bulletedList) {
                            dataOutputStream.writeUTF(string);
                        }
                    }
                    case EDUCATION, EXPERIENCE -> {
                        OrganizationSection organizationSection = (OrganizationSection) entry.getValue();
                        List<Organization> organizations = organizationSection.getOrganizations();
                        dataOutputStream.writeInt(organizations.size());
                        for (Organization organization : organizations) {
                            dataOutputStream.writeUTF(organization.getHomePage().getOrganizationName());
                            String url = organization.getHomePage().getUrl();
                            if (url == null) {
                                dataOutputStream.writeUTF("");
                            } else {
                                dataOutputStream.writeUTF(url);
                            }
                            List<Organization.Position> positions = organization.getPositions();
                            dataOutputStream.writeInt(positions.size());
                            for (Organization.Position position : positions) {
                                dataOutputStream.writeUTF(position.getPosition());
                                String description = position.getDescription();
                                if (description == null) {
                                    dataOutputStream.writeUTF("");
                                } else {
                                    dataOutputStream.writeUTF(description);
                                }
                                dataOutputStream.writeUTF(String.valueOf(position.getStartDate()));
                                dataOutputStream.writeUTF(String.valueOf(position.getEndDate()));
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public Resume readResume(InputStream inputStream) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            String uuid = dataInputStream.readUTF();
            String fullName = dataInputStream.readUTF();
            Resume resume = new Resume(uuid, fullName);

            int contactsCount = dataInputStream.readInt();
            for (int i = 0; i < contactsCount; i++) {
                ContactType contactType = ContactType.valueOf(dataInputStream.readUTF());
                String value = dataInputStream.readUTF();
                resume.addContact(contactType, value);
            }

            int sectionsCount = dataInputStream.readInt();
            for (int i = 0; i < sectionsCount; i++) {
                SectionType sectionType = SectionType.valueOf(dataInputStream.readUTF());
                switch (sectionType) {
                    case OBJECTIVE, PERSONAL -> {
                        TextSection textSection = new TextSection(dataInputStream.readUTF());
                        resume.addSectionData(sectionType, textSection);
                    }
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> bulletedList = new ArrayList<>();
                        int bulletedListCount = dataInputStream.readInt();
                        for (int j = 0; j < bulletedListCount; j++) {
                            bulletedList.add(dataInputStream.readUTF());
                        }
                        ListSection listSection = new ListSection(bulletedList);
                        resume.addSectionData(sectionType, listSection);
                    }
                    case EDUCATION, EXPERIENCE -> {
                        List<Organization> organizations = new ArrayList<>();
                        int organizationsCount = dataInputStream.readInt();
                        for (int j = 0; j < organizationsCount; j++) {
                            String organizationName = dataInputStream.readUTF();
                            String url = dataInputStream.readUTF();
                            Link homePage = new Link(organizationName, url);
                            List<Organization.Position> positions = new ArrayList<>();
                            int positionsCount = dataInputStream.readInt();
                            for (int k = 0; k < positionsCount; k++) {
                                String position = dataInputStream.readUTF();
                                String description = dataInputStream.readUTF();
                                LocalDate startDate = LocalDate.parse(dataInputStream.readUTF());
                                LocalDate endDate = LocalDate.parse(dataInputStream.readUTF());
                                positions.add(new Organization.Position(startDate, endDate, position, description));
                            }
                            Organization organization = new Organization(homePage, positions);
                            organizations.add(organization);
                            OrganizationSection organizationSection = new OrganizationSection(organizations);
                            resume.addSectionData(sectionType, organizationSection);
                        }
                    }
                }
            }
            return resume;
        }
    }
}
