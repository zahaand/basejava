package com.zahaand.webapp.model;

public enum ResumeContacts {
    PHONE_NUMBER("Номер телефона"),
    SKYPE("Srype"),
    EMAIL("e-mail"),
    LINKEDIN("Linkedin"),
    GITHUB("GitHub"),
    STACKOVERFLOW("Stackoverflow"),
    HOMEPAGE("Home page");

    private final String title;

    ResumeContacts(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
