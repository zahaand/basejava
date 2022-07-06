package com.zahaand.webapp.web;

import com.zahaand.webapp.Config;
import com.zahaand.webapp.model.AbstractSection;
import com.zahaand.webapp.model.ContactType;
import com.zahaand.webapp.model.Resume;
import com.zahaand.webapp.model.SectionType;
import com.zahaand.webapp.storage.Storage;
import com.zahaand.webapp.util.JsonParser;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume resume = storage.get(uuid);
        resume.setFullName(fullName);
        for (ContactType contactType : ContactType.values()) {
            String contact = request.getParameter(contactType.name());
            if (contact != null && contact.trim().length() != 0) {
                resume.setContact(contactType, contact);
            } else {
                resume.getContacts().remove(contactType);
            }
        }
        for (SectionType sectionType : SectionType.values()) {
            String section = request.getParameter(sectionType.name());
            if (section != null && section.trim().length() != 0) {
                resume.setSection(sectionType, JsonParser.read(section, AbstractSection.class));
            } else {
                resume.getSections().remove(sectionType);
            }
        }
        storage.update(resume);
        response.sendRedirect("resume");
    }
}
