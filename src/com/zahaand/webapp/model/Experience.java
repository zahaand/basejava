package com.zahaand.webapp.model;

import java.time.LocalDate;

public class Experience {
    private String header;
    private String text;
    private LocalDate startDate;
    private LocalDate endDate;

    public Experience(String header, String text, LocalDate startDate, LocalDate endDate) {
        this.header = header;
        this.text = text;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return header + "\n" + startDate + " - " + endDate + "\n" + text + "\n";
    }
}
