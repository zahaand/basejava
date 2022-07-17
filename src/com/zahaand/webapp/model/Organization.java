package com.zahaand.webapp.model;

import com.zahaand.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.zahaand.webapp.util.DateUtil.NOW;
import static com.zahaand.webapp.util.DateUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private Link homePage;
    private List<Position> positions;
    private static final long SERIALIZABLE_VERSION = 1L;

    public static final Organization EMPTY = new Organization("", "", Position.EMPTY);

    public Organization() {
    }

    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public Organization(Link homePage, List<Position> positions) {
        Objects.requireNonNull(positions, "experience must not be null");
        this.homePage = homePage;
        this.positions = positions;
    }

    @Override
    public String toString() {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(homePage);
//        for (Position position : positions) {
//            stringBuilder.append(position);
//        }
//        return stringBuilder.toString();
        return homePage + "\n" + positions;
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

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {
        private String position;
        private String description;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;
        private static final long SERIALIZABLE_VERSION = 1L;

        public static final Position EMPTY = new Position();

        public Position() {
        }

        public Position(int startYear, Month startMonth, String position, String description) {
            this(of(startYear, startMonth), NOW, position, description);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String position, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), position, description);
        }

        public String getPosition() {
            return position;
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
