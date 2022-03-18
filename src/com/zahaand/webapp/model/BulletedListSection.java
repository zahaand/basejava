package com.zahaand.webapp.model;

import java.util.List;

public class BulletedListSection extends AbstractSection {
    List<String> bulletedList;

    public BulletedListSection(List<String> bulletedList) {
        this.bulletedList = bulletedList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : bulletedList) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}