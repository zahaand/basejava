package com.zahaand.webapp.web;

import com.zahaand.webapp.Config;
import com.zahaand.webapp.model.*;
import com.zahaand.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        storage = Config.getInstance().getSqlStorage();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        Resume resume;
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        switch (action) {
            case "get", "edit" -> resume = storage.get(uuid);
            case "delete" -> {
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            }
            case "add" -> {
                resume = new Resume("");
                storage.save(resume);
                response.sendRedirect("resume?uuid=" + resume.getUuid() + "&action=edit");
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
        Resume resume = storage.get(uuid);
        resume.setFullName(fullName);
        for (ContactType contactType : ContactType.values()) {
            String contact = request.getParameter(contactType.name());
            if (contact == null) {
                resume.getContacts().remove(contactType);
            } else {
                resume.setContact(contactType, contact);
            }
        }
        for (SectionType sectionType : SectionType.values()) {
            String section = request.getParameter(sectionType.name());
            if (section == null) {
                resume.getSections().remove(sectionType);
            } else {
                switch (sectionType) {
                    case OBJECTIVE, PERSONAL -> resume.setSection(sectionType, new TextSection(section));
                    case ACHIEVEMENT, QUALIFICATIONS ->
                            resume.setSection(sectionType, new ListSection(Arrays.stream(section.split("\\n")).toList()));
                    case EXPERIENCE, EDUCATION -> {
                        List<Organization> organizations = new ArrayList<>();
                        List<Organization.Position> positions = new ArrayList<>();
                        String[] parameterValues = request.getParameterValues(sectionType.name());
                        String[] urls = request.getParameterValues("url");
                        for (int i = 0; i < parameterValues.length; i++) {
                            String name = parameterValues[i];
                            if (name != null) {
                                String[] startDates = request.getParameterValues("startDate");
                                String[] endDates = request.getParameterValues("endDate");
                                String[] titles = request.getParameterValues("titles");
                                String[] descriptions = request.getParameterValues("description");
                                for (int j = 0; j < titles.length; j++) {
                                    if (titles[j] != null) {
                                        positions.add(new Organization.Position(LocalDate.parse(startDates[j]), LocalDate.parse(endDates[j]), titles[j], descriptions[j]));
                                    }
                                }
                                organizations.add(new Organization(new Link(name, urls[i]), positions));
                            }
                        }
                        resume.setSection(sectionType, new OrganizationSection(organizations));
                    }
                }
            }
        }
        storage.update(resume);
        response.sendRedirect("resume");
    }
}
