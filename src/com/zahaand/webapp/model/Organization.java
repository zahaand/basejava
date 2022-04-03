package com.zahaand.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.zahaand.webapp.util.DateUtil.NOW;
import static com.zahaand.webapp.util.DateUtil.of;

public class Organization implements Serializable {
    private final Link homePage;
    private final List<Position> positions;
    private static final long SERIALIZABLE_VERSION = 1L;

    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Organization(Link homePage, List<Position> positions) {
        Objects.requireNonNull(positions, "experience must not be null");
        this.homePage = homePage;
        this.positions = positions;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(homePage);
        for (Position position : positions) {
            stringBuilder.append(position);
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return homePage.equals(that.homePage) && Objects.equals(positions, that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, positions);
    }

    public static class Position implements Serializable {
        private final String position;
        private final String description;
        private final LocalDate startDate;
        private final LocalDate endDate;
        private static final long SERIALIZABLE_VERSION = 1L;

        public Position(int startYear, Month startMonth, String position, String description) {
            this(of(startYear, startMonth), NOW, position, description);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String position, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), position, description);
        }

        public Position(LocalDate startDate, LocalDate endDate, String position, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(position, "position must not be null");
            this.position = position;
            this.description = description;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        @Override
        public String toString() {
            return startDate + " - " + endDate + "\n" + position + "\n" + description + "\n";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position that = (Position) o;
            return position.equals(that.position) && Objects.equals(description, that.description) && startDate.equals(that.startDate) && endDate.equals(that.endDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(position, description, startDate, endDate);
        }
    }
}
