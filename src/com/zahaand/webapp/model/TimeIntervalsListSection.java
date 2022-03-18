package com.zahaand.webapp.model;

import java.util.List;

public class TimeIntervalsListSection extends AbstractSection {
    private List<TimeIntervalSection> timeIntervalSections;

    public TimeIntervalsListSection(List<TimeIntervalSection> timeIntervalSections) {
        this.timeIntervalSections = timeIntervalSections;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (TimeIntervalSection section : timeIntervalSections) {
            stringBuilder.append(section).append("\n");
        }
        return stringBuilder.toString();
    }
}
