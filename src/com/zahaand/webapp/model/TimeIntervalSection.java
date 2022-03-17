package com.zahaand.webapp.model;

import java.time.LocalDate;

public class TimeIntervalSection {
    String header;
    String text;
    LocalDate beginning;
    LocalDate end;

    public TimeIntervalSection(String header, String text, LocalDate beginning, LocalDate end) {
        this.header = header;
        this.text = text;
        this.beginning = beginning;
        this.end = end;
    }
}
