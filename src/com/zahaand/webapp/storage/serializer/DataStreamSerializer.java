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
                        dataOutputStream.writeInt(listSection.getBulletedList().size());
                        for (String s : listSection.getBulletedList()) {
                            dataOutputStream.writeUTF(s);
                        }
                    }
                    case EDUCATION, EXPERIENCE -> {
                        OrganizationSection organizationSection = (OrganizationSection) entry.getValue();
                        List<Organization> organizations = organizationSection.getOrganizations();
                        dataOutputStream.writeInt(organizations.size());
                        for (Organization organization : organizations) {
                            dataOutputStream.writeUTF((organization.getHomePage().getOrganizationName()));
                            dataOutputStream.writeUTF((organization.getHomePage().getUrl()));
                            List<Organization.Position> positions = organization.getPositions();
                            dataOutputStream.writeInt(positions.size());
                            for (Organization.Position position : positions) {
                                dataOutputStream.writeUTF(position.getPosition());
                                dataOutputStream.writeUTF(position.getDescription());
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
                        for (int j = 0; j < dataInputStream.readInt(); j++) {
                            bulletedList.add(dataInputStream.readUTF());
                        }
                        ListSection listSection = new ListSection(bulletedList);
                        resume.addSectionData(sectionType, listSection);
                    }
                    case EDUCATION, EXPERIENCE -> {
                        List<Organization> organizations = new ArrayList<>();
                        for (int j = 0; j < dataInputStream.readInt(); j++) {
                            String organizationName = dataInputStream.readUTF();
                            String url = dataInputStream.readUTF();
                            Link homePage = new Link(organizationName, url);
                            List<Organization.Position> positions = new ArrayList<>();
                            for (int k = 0; k < dataInputStream.readInt(); k++) {
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
