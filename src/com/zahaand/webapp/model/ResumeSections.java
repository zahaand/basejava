package com.zahaand.webapp.model;

public enum ResumeSections {
    PERSONAL("Личные качества"),
    OBJECTIVE("Позиция"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private final String title;

    ResumeSections(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

