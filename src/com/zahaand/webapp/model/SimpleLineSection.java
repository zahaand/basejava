package com.zahaand.webapp.model;

public class SimpleLineSection extends AbstractSection {
    private String text;

    public SimpleLineSection(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
