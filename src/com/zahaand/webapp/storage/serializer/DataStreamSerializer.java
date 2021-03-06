package com.zahaand.webapp.storage.serializer;

import com.zahaand.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void writeResume(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.writeUTF(resume.getUuid());
            dataOutputStream.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();
            writeCollection(contacts.entrySet(), dataOutputStream, contact -> {
                dataOutputStream.writeUTF(contact.getKey().name());
                dataOutputStream.writeUTF(contact.getValue());
            });

            Map<SectionType, AbstractSection> sections = resume.getSections();
            writeCollection(sections.entrySet(), dataOutputStream, section -> {
                dataOutputStream.writeUTF(section.getKey().name());
                switch (section.getKey()) {
                    case OBJECTIVE, PERSONAL -> dataOutputStream.writeUTF(String.valueOf(section.getValue()));
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        ListSection listSection = (ListSection) section.getValue();
                        List<String> bulletedList = listSection.getBulletedList();
                        writeCollection(bulletedList, dataOutputStream, dataOutputStream::writeUTF);
                    }
                    case EDUCATION, EXPERIENCE -> {
                        OrganizationSection organizationSection = (OrganizationSection) section.getValue();
                        List<Organization> organizations = organizationSection.getOrganizations();
                        writeCollection(organizations, dataOutputStream, organization -> {
                            dataOutputStream.writeUTF(organization.getHomePage().getOrganizationName());
                            String url = organization.getHomePage().getUrl();
                            writeStringIfNull(url, dataOutputStream);
                            List<Organization.Position> positions = organization.getPositions();
                            writeCollection(positions, dataOutputStream, position -> {
                                dataOutputStream.writeUTF(position.getPosition());
                                String description = position.getDescription();
                                writeStringIfNull(description, dataOutputStream);
                                writeLocalDate(position.getStartDate(), dataOutputStream);
                                writeLocalDate(position.getEndDate(), dataOutputStream);
                            });
                        });
                    }
                }
            });
        }
    }

    private <T> void writeCollection(Collection<T> collection, DataOutputStream dataOutputStream, ElementWriter<T> writer) throws IOException {
        dataOutputStream.writeInt(collection.size());
        for (T t : collection) {
            writer.write(t);
        }
    }

    @FunctionalInterface
    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private void writeStringIfNull(String url, DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(url == null ? "" : url);
    }

    private void writeLocalDate(LocalDate date, DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(String.valueOf(date));
    }

    @Override
    public Resume readResume(InputStream inputStream) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            String uuid = dataInputStream.readUTF();
            String fullName = dataInputStream.readUTF();
            Resume resume = new Resume(uuid, fullName);

            readElements(dataInputStream, () -> {
                ContactType contactType = ContactType.valueOf(dataInputStream.readUTF());
                String value = dataInputStream.readUTF();
                resume.setContact(contactType, value);
            });

            readElements(dataInputStream, () -> {
                SectionType sectionType = SectionType.valueOf(dataInputStream.readUTF());
                resume.setSection(sectionType, readSection(sectionType, dataInputStream));
            });
            return resume;
        }
    }

    private AbstractSection readSection(SectionType sectionType, DataInputStream dataInputStream) throws IOException {
        switch (sectionType) {
            case OBJECTIVE, PERSONAL -> {
                return new TextSection(dataInputStream.readUTF());
            }
            case ACHIEVEMENT, QUALIFICATIONS -> {
                List<String> bulletedList = new ArrayList<>();
                readElements(dataInputStream, () -> bulletedList.add(dataInputStream.readUTF()));
                return new ListSection(bulletedList);
            }
            case EDUCATION, EXPERIENCE -> {
                List<Organization> organizations = new ArrayList<>();
                readElements(dataInputStream, () -> {
                    String organizationName = dataInputStream.readUTF();
                    String url = readStringIfNull(dataInputStream);
                    Link homePage = new Link(organizationName, url);
                    List<Organization.Position> positions = new ArrayList<>();
                    readElements(dataInputStream, () -> {
                        String position = dataInputStream.readUTF();
                        String description = readStringIfNull(dataInputStream);
                        LocalDate startDate = readLocalDate(dataInputStream);
                        LocalDate endDate = readLocalDate(dataInputStream);
                        positions.add(new Organization.Position(startDate, endDate, position, description));
                    });
                    Organization organization = new Organization(homePage, positions);
                    organizations.add(organization);
                });
                return new OrganizationSection(organizations);
            }
            default -> throw new IllegalStateException("Unexpected value: " + sectionType);
        }
    }

    private void readElements(DataInputStream dataInputStream, ElementReader reader) throws IOException {
        int size = dataInputStream.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }

    @FunctionalInterface
    private interface ElementReader {
        void read() throws IOException;
    }

    private String readStringIfNull(DataInputStream dataInputStream) throws IOException {
        String result = dataInputStream.readUTF();
        return result.equals("") ? null : result;
    }

    private LocalDate readLocalDate(DataInputStream dataInputStream) throws IOException {
        return LocalDate.parse(dataInputStream.readUTF());
    }
}


