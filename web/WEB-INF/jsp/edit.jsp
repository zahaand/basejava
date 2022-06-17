<%@ page import="com.zahaand.webapp.model.ContactType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Edit resume</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"></jsp:include>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <jsp:useBean id="resume" scope="request" type="com.zahaand.webapp.model.Resume"/>
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" name="fullName" size="50" value="${resume.fullName}"></dd>
        </dl>
        <h3>Contacts:</h3>
        <c:forEach var="type" items="${ContactType.values()}">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size="30" value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <h3>Sections:</h3>
        <input type="text" name="section" size="30" value="1"><br/>
        <input type="text" name="section" size="30" value="2"><br/>
        <input type="text" name="section" size="30" value="3"><br/>
        <hr/>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Back</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
</html>
