package com.zahaand.webapp.model;

import java.util.List;

public class TimeIntervalListSection extends AbstractSection {
    private List<TimeIntervalSection> timeIntervalSections;

    public TimeIntervalListSection(List<TimeIntervalSection> timeIntervalSections) {
        this.timeIntervalSections = timeIntervalSections;
    }
}
