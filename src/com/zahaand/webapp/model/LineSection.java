package com.zahaand.webapp.model;

import java.util.Objects;

public class LineSection extends AbstractSection {
    private final String text;
    private static final long SERIALIZABLE_VERSION = 1L;

    public LineSection(String text) {
        Objects.requireNonNull(text, "line must not be null");
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineSection that = (LineSection) o;
        return text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
