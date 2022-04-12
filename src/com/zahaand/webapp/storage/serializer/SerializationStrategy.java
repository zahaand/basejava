package com.zahaand.webapp.storage.serializer;

import com.zahaand.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface SerializationStrategy {

    void writeResume(Resume resume, OutputStream outputStream) throws IOException;

    Resume readResume(InputStream inputStream) throws IOException;
}
