package com.zahaand.webapp.model;

import java.util.List;

public class Organization extends AbstractSection {
    private List<Experience> experiences;

    public Organization(List<Experience> experiences) {
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
}
