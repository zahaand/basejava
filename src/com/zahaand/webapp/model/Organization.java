package com.zahaand.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link link;
    private final List<Experience> experiences;

    public Organization(Link link, List<Experience> experiences) {
        Objects.requireNonNull(experiences, "experience must not be null");
        this.link = link;
        this.experiences = experiences;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(link);
        for (Experience experience : experiences) {
            stringBuilder.append(experience);
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return link.equals(that.link) && Objects.equals(experiences, that.experiences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, experiences);
    }
}
