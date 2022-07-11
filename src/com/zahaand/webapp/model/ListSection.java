package com.zahaand.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private List<String> bulletedList;
    private static final long SERIALIZABLE_VERSION = 1L;

    public ListSection() {
    }

    public ListSection(List<String> bulletedList) {
        Objects.requireNonNull(bulletedList, "list must not be null");
        this.bulletedList = bulletedList;
    }

    public List<String> getBulletedList() {
        return bulletedList;
    }

    @Override
    public String toString() {
        if (bulletedList == null) {
            return "";
        }
        return bulletedList.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return bulletedList.equals(that.bulletedList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bulletedList);
    }
}
