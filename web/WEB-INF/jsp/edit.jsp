<%@ page import="com.zahaand.webapp.model.ContactType" %>
<%@ page import="com.zahaand.webapp.model.SectionType" %>
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
                <dd><input type="text" name="${type}" size="30" value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <h3>Sections:</h3>
        <c:forEach var="type" items="${SectionType.values()}">
            <c:set var="section" value="${resume.getSectionType(type)}"></c:set>
            <jsp:useBean id="type" type="com.zahaand.webapp.model.SectionType"></jsp:useBean>
            <c:choose>
                <c:when test="${type != 'EXPERIENCE' && type != 'EDUCATION'}">
                    <h4>${type.title}</h4>
                    <textarea name="${type.title}">
                            ${resume.getSectionType(type)}
                    </textarea>
                </c:when>
                <c:otherwise>
                    <c:forEach var="organization" items="${resume.getSectionType(type).getOrganizations()}">
                        <dl>
                            <dt>Organization:</dt>
                            <dd><input type="text" name="${type.title}" size="30"
                                       value="${organization.getHomePage().getOrganizationName()}"></dd>
                        </dl>
                        <dl>
                            <dt>web-site:</dt>
                            <dd><input type="text" name="${type.title}_url" size="30"
                                       value="${organization.getHomePage().getUrl()}"></dd>
                        </dl>
                        <div>
                            <c:forEach var="position" items="${organization.getPositions()}">
                                <jsp:useBean id="position"
                                             type="com.zahaand.webapp.model.Organization.Position"></jsp:useBean>
                                <dl>start date:</dl>
                                <dd><input type="text" name="${type.title}_startDate" size="10"
                                           value="${position.startDate}">
                                </dd>
                                <dl>end date:</dl>
                                <dd><input type="text" name="${type.title}_endDate" size="10"
                                           value="${position.endDate}">
                                </dd>
                                <dl>position:</dl>
                                <dd><input type="text" name="${type.title}_position" size="30"
                                           value="${position.position}">
                                </dd>
                                <dl>description:</dl>
                                <dd><textarea name="${type.title}_description" rows="5"
                                              cols="30">${position.description}</textarea>
                                </dd>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <hr/>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="reset">Cancel</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
</html>
