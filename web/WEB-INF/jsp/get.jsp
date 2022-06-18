<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Get reume</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"></jsp:include>
<section>
    <jsp:useBean id="resume" scope="request" type="com.zahaand.webapp.model.Resume"/>
    <h2>${resume.fullName}&nbsp;<a href="resume.uuid=${resume.uuid}&action=edit"><img src="img/edit.png"></a></h2>
    <p>
        <c:forEach var="contactsEntry" items="${resume.contacts}">
            <jsp:useBean id="contactsEntry"
                         type="java.util.Map.Entry<com.zahaand.webapp.model.ContactType, java.lang.String>"></jsp:useBean>
            ${contactsEntry.key}
            ${contactsEntry.value}
            <br/>
        </c:forEach>
    </p>
</section>
<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
</html>
