package com.zahaand.webapp.web;

import com.zahaand.webapp.Config;
import com.zahaand.webapp.model.*;
import com.zahaand.webapp.storage.Storage;
import com.zahaand.webapp.util.DateUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        storage = Config.getInstance().getSqlStorage();
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "get" -> resume = storage.get(uuid);
            case "add" -> resume = new Resume();
            case "edit" -> {
                resume = storage.get(uuid);
//                for (SectionType sectionType : new SectionType[]{SectionType.EXPERIENCE, SectionType.EDUCATION}) {
//                    OrganizationSection section = (OrganizationSection) resume.getSection(sectionType);
//                    List<Organization> emptyFirstOrganizations = new ArrayList<>();
//                    emptyFirstOrganizations.add(Organization.EMPTY);
//                    if (section != null) {
//                        for (Organization organization : section.getOrganizations()) {
//                            List<Organization.Position> emptyFirstPositions = new ArrayList<>();
//                            emptyFirstPositions.add(Organization.Position.EMPTY);
//                            emptyFirstPositions.addAll(organization.getPositions());
//                            emptyFirstOrganizations.add(new Organization(organization.getHomePage(), emptyFirstPositions));
//                        }
//                    }
//                    resume.setSection(sectionType, new OrganizationSection(emptyFirstOrganizations));
//                }
            }
            case "delete" -> {
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            }
            default -> throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher((action.equals("get")) ? "/WEB-INF/jsp/get.jsp" : "/WEB-INF/jsp/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        boolean isCreate = !uuid.equals("");
        Resume resume = isCreate ? storage.get(uuid) : new Resume("");
        resume.setFullName(fullName);
        for (ContactType contactType : ContactType.values()) {
            String contactValue = request.getParameter(contactType.name());
            if (contactValue == null) {
                resume.getContacts().remove(contactType);
            } else {
                resume.setContact(contactType, contactValue);
            }
        }
        for (SectionType sectionType : SectionType.values()) {
            String sectionValue = request.getParameter(sectionType.getTitle());
            if (sectionValue == null) {
                resume.getSections().remove(sectionType);
            } else {
                switch (sectionType) {
                    case OBJECTIVE, PERSONAL -> resume.setSection(sectionType, new TextSection(sectionValue));
                    case ACHIEVEMENT, QUALIFICATIONS ->
                            resume.setSection(sectionType, new ListSection(List.of(sectionValue.trim().split("\\n"))));
                    case EXPERIENCE, EDUCATION -> {
                        List<Organization> organizations = new ArrayList<>();
                        String[] urls = request.getParameterValues("url" + sectionType.getTitle());
                        String[] sectionValues = request.getParameterValues(sectionType.getTitle());
                        for (int i = 0; i < sectionValues.length; i++) {
                            String organization = sectionValues[i];
                            if (organization != null) {
                                List<Organization.Position> positions = new ArrayList<>();
                                String[] startDates = request.getParameterValues("startDate" + organization + i);
                                String[] endDates = request.getParameterValues("endDate" + organization + i);
                                String[] titles = request.getParameterValues("position" + organization + i);
                                String[] descriptions = request.getParameterValues("description" + organization + i);
                                if (titles != null) {
                                    for (int j = 0; j < titles.length; j++) {
                                        positions.add(new Organization.Position(DateUtil.parse(startDates[j]), DateUtil.parse(endDates[j]), titles[j], descriptions[j]));
                                    }
                                }
                                organizations.add(new Organization(new Link(organization, urls[i]), positions));
                            }
                        }
                        resume.setSection(sectionType, new OrganizationSection(organizations));
                    }
                }
            }
        }
        if (!isCreate) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }
        response.sendRedirect("resume");
    }
}