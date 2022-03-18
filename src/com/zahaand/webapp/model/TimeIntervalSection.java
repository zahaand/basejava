package com.zahaand.webapp.model;

import java.time.LocalDate;

public class TimeIntervalSection {
    private String header;
    private String text;
    private LocalDate startDate;
    private LocalDate endDate;
    private String text2;
    private LocalDate startDate2;
    private LocalDate endDate2;

    public TimeIntervalSection(String header, String text, LocalDate startDate, LocalDate endDate) {
        this.header = header;
        this.text = text;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TimeIntervalSection(String header, String text, LocalDate startDate, LocalDate endDate, String text2, LocalDate startDate2, LocalDate endDate2) {
        this.header = header;
        this.text = text;
        this.startDate = startDate;
        this.endDate = endDate;
        this.text2 = text2;
        this.startDate2 = startDate2;
        this.endDate2 = endDate2;
    }
}
