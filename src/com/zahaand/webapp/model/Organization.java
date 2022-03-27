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
        String organization = "dummy";
        for (Experience experience : experiences) {
            if (experience.getOrganization().equals(organization)) {
                stringBuilder.append("\n").append(experience.getStartDate()).append(" - ").append("\n").append(experience.getDescription()).append("\n");
            } else {
                stringBuilder.append(experience.getHomePage()).append("\n").append(experience.getOrganization()).append("\n").append(experience).append("\n");
            }
            organization = experience.getOrganization();
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
