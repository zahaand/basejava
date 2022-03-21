package com.zahaand.webapp.model;

import java.util.Objects;

public class SimpleLineSection extends AbstractSection {
    private final String text;

    public SimpleLineSection(String text) {
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
        SimpleLineSection that = (SimpleLineSection) o;
        return text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
