package com.zahaand.webapp.model;

import java.util.Objects;

public class Link {
    private final String organizationName;
    private final String url;

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
