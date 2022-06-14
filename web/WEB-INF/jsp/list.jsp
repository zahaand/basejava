<%@ page import="com.zahaand.webapp.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Resumes</title>
</head>
<body>
<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th></th>
            <th></th>
        </tr>
        <jsp:useBean id="resumes" scope="request" type="java.util.List"/>
        <c:forEach items="${resumes}" var="resume">
            <jsp:useBean id="resume" type="com.zahaand.webapp.model.Resume"/>
            <tr>
                <td><a href="resume?uuid = ${resume.uuid}">${resume.fullName}</a></td>
                <td>${resume.getContact(ContactType.EMAIL) == null ? "" : resume.getContact(ContactType.EMAIL)}</td>
                <td><a href="resume?uuid = ${resume.uuid}">Delete</a></td>
                <td><a href="resume?uuid = ${resume.uuid}">Edit</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td><a href="resume?add">Add resume</a></td>
        </tr>
    </table>
</section>
</body>
</html>
