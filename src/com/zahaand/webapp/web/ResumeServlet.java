package com.zahaand.webapp.web;

import com.zahaand.webapp.model.Resume;
import com.zahaand.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String parameter = request.getParameter("uuid");
        Writer writer = response.getWriter();
        if (parameter == null) {
            writer.write(
                    "<html>" +
                            "<head>" +
                            "<title>All Resumes</title>" +
                            "</head>" +
                            "<body>" +
                            "<table>" +
                            "<tr>" +
                            "<th>uuid</th>" +
                            "<th>Full Name</th>" +
                            "</tr>");
            for (Resume resume : storage.getAllSorted()) {
                String uuid = resume.getUuid();
                String fullName = resume.getFullName();
                writer.write(
                        "<tr>" +
                                "<td>" + uuid + "</td>" +
                                "<td>" + fullName + "</td>" +
                                "</tr>"
                );
            }
            writer.write(
                    "</table>" +
                            "</body>" +
                            "</html>"
            );
        } else {
            writer.write(storage.get(parameter).toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
