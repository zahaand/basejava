<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Resumes</title>
</head>
<body>
<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <jsp:useBean id="resumes" scope="request" type="com.zahaand.webapp.model.Resume"/>
        <jsp:useBean id="ContactType" scope="request" type="com.zahaand.webapp.model.ContactType"/>
        <tr>
            <th>Name</th>
            <th>Email</th>
        </tr>
        <c:forEach items="${resumes}" var="resume">
            <tr>
                <td><a href="resume?uuid=${resume.uuid}">${resume.fullName}</a></td>
                <td>${resume.getContact(ContactType.EMAIL)}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
