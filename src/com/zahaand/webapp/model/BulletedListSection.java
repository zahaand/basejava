package com.zahaand.webapp.model;

import java.util.List;
import java.util.Objects;

public class BulletedListSection extends AbstractSection {
    private final List<String> bulletedList;

    public BulletedListSection(List<String> bulletedList) {
        Objects.requireNonNull(bulletedList, "list must not be null");
        this.bulletedList = bulletedList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : bulletedList) {
            stringBuilder.append(s).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BulletedListSection that = (BulletedListSection) o;
        return bulletedList.equals(that.bulletedList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bulletedList);
    }
}
