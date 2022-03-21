package com.zahaand.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization extends AbstractSection {
    private final List<Experience> experiences;

    public Organization(List<Experience> experiences) {
        Objects.requireNonNull(experiences, "experience must not be null");
        this.experiences = experiences;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Experience section : experiences) {
            stringBuilder.append(section).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return experiences.equals(that.experiences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(experiences);
    }
}
