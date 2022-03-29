package com.zahaand.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Experience {
    private final String organization;
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Experience(String organization, String description, LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(organization, "organization must not be null");
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        this.organization = organization;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return startDate + " - " + endDate + "\n" + description + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return organization.equals(that.organization) && Objects.equals(description, that.description) && startDate.equals(that.startDate) && endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organization, description, startDate, endDate);
    }
}
