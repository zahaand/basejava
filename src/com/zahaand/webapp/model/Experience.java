package com.zahaand.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Experience {
    private final Link homePage;
    private final String organization;
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Experience(String name, String url, String organization, String description, LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(organization, "title must not be null");
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        this.homePage = new Link(name, url);
        this.organization = organization;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Link getHomePage() {
        return homePage;
    }

    public String getOrganization() {
        return organization;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
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

        if (!homePage.equals(that.homePage)) return false;
        if (!organization.equals(that.organization)) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (!startDate.equals(that.startDate)) return false;
        return endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + organization.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        return result;
    }
}
