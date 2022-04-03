package com.zahaand.webapp.model;

import java.io.Serializable;
import java.util.Objects;

public class Link implements Serializable {
    private final String organizationName;
    private final String url;
    private static final long SERIALIZABLE_VERSION = 1L;

    public Link(String organizationName, String url) {
        Objects.requireNonNull(organizationName, "organization name must not be null");
        this.organizationName = organizationName;
        this.url = url;
    }

    @Override
    public String toString() {
        return organizationName + "\n" + url + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return organizationName.equals(link.organizationName) && Objects.equals(url, link.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationName, url);
    }
}
