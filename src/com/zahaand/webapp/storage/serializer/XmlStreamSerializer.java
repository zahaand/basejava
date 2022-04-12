package com.zahaand.webapp.storage.serializer;

import com.zahaand.webapp.model.*;
import com.zahaand.webapp.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlStreamSerializer implements SerializationStrategy {
    private XmlParser xmlParser;

    public XmlStreamSerializer() {
        xmlParser = new XmlParser(Resume.class, Organization.class, Link.class, Organization.Position.class, LineSection.class, ListSection.class, OrganizationSection.class);
    }

    @Override
    public void writeResume(Resume resume, OutputStream outputStream) throws IOException {
        try (Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            xmlParser.marshall(resume, writer);
        }
    }

    @Override
    public Resume readResume(InputStream inputStream) throws IOException {
        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(reader);

        }
    }
}
