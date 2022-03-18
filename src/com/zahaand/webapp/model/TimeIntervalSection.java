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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (text2 != null) {
            stringBuilder.append(header).append("\n")
                    .append(startDate).append(" - ").append(endDate).append("\n").append(text).append("\n")
                    .append(startDate2).append(" - ").append(endDate2).append("\n").append(text2).append("\n");
        } else {
            stringBuilder.append(header).append("\n")
                    .append(startDate).append(" - ").append(endDate).append("\n").append(text).append("\n");
        }
        return stringBuilder.toString();
    }
}
