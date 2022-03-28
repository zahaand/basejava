package com.zahaand.webapp.model;

import java.util.List;

public class OrganizationsList extends AbstractSection {
    private final List<Organization> organizations;

    public OrganizationsList(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Organization organization : organizations) {
            stringBuilder.append(organization).append("\n");
        }
        return stringBuilder.toString();
    }
}
