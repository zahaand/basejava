<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.zahaand.webapp.model.ListSection" %>
<%@ page import="com.zahaand.webapp.model.OrganizationSection" %>
<%@ page import="com.zahaand.webapp.model.TextSection" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Get resume</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"></jsp:include>
<section>
    <jsp:useBean id="resume" scope="request" type="com.zahaand.webapp.model.Resume"/>
    <h1>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/edit.png" alt="Edit"></a>
    </h1>
    <p>
        <c:forEach var="contactsEntry" items="${resume.contacts}">
            <jsp:useBean id="contactsEntry"
                         type="java.util.Map.Entry<com.zahaand.webapp.model.ContactType, java.lang.String>"></jsp:useBean>
            ${contactsEntry.key}
            ${contactsEntry.value}
            <br/>
        </c:forEach>
    </p>
    <table cellspacing="2">
        <c:forEach var="sectionsEntry" items="${resume.sections}">
            <jsp:useBean id="sectionsEntry"
                         type="java.util.Map.Entry<com.zahaand.webapp.model.SectionType, com.zahaand.webapp.model.AbstractSection>"/>
            <c:set var="type" value="${sectionsEntry.key}"></c:set>
            <c:set var="section" value="${sectionsEntry.value}"></c:set>
            <jsp:useBean id="section" type="com.zahaand.webapp.model.AbstractSection"></jsp:useBean>
            <tr>
                <td colspan="2">
                    <h2><a name="type_name" ${type.title}></a></h2>
                </td>
            </tr>
            <c:choose>
                <c:when test="${type=='OBJECTIVE' || type=='PERSONAL'}">
                    <tr>
                        <td colspan="2">
                                ${section.toString()}
                        </td>
                    </tr>
                </c:when>
                <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
                    <tr>
                        <td colspan="2">
                            <ul>
                                <c:forEach var="item" items="<%=((ListSection) section).getBulletedList()%>">
                                    <li>${item}</li>
                                </c:forEach>
                            </ul>
                        </td>
                    </tr>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="organization" items="<%=((OrganizationSection) section).getOrganizations()%>">
                        <tr>
                            <td colspan="2">
                                <c:choose>
                                    <c:when test="${empty organization.homePage.url}">
                                        <h3>${organization.homePage.url}</h3>
                                    </c:when>
                                    <c:otherwise>
                                        <h3><a href="${organization.homePage.url}">${organization.homePage.name}</a>
                                        </h3>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <c:forEach var="position" items="${organization.positions}">
                            <jsp:useBean id="position"
                                         type="com.zahaand.webapp.model.Organization.Position"></jsp:useBean>
                            <tr>
                                <td><${position}></td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
    </table>
    <button onclick="window.history.back()">OK</button>
</section>
<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
</html>
