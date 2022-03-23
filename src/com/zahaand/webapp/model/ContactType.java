package com.zahaand.webapp.model;

public enum ContactType {
    PHONE_NUMBER("Номер телефона"),
    SKYPE("Skype"),
    EMAIL("e-mail"),
    LINKEDIN("Linkedin"),
    GITHUB("GitHub"),
    STACKOVERFLOW("Stackoverflow"),
    HOMEPAGE("Home page");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
