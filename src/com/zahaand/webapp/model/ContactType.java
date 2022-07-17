package com.zahaand.webapp.model;

public enum ContactType {
    PHONE_NUMBER("Phone number"),
    SKYPE("Skype"),
    EMAIL("E-mail"),
    LINKEDIN("Linkedin"),
    GITHUB("GitHub"),
    STACKOVERFLOW("Stackoverflow"),
    HOMEPAGE("Homepage");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
