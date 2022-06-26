<%@ page import="com.zahaand.webapp.model.ContactType" %>
<%@ page import="com.zahaand.webapp.model.SectionType" %>
<%@ page import="com.zahaand.webapp.util.DateUtil" %>
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
        <c:forEach var="sections" items="${SectionType.values()}">
            <c:set var="type" value="${resume.getSectionType(sections)}"></c:set>
            <jsp:useBean id="section" type="com.zahaand.webapp.model.SectionType"></jsp:useBean>
            <c:choose>
                <c:when test="${type=='OBJECTIVE' || type=='PERSONAL'}">
                    <input type="text" name="${sections}" size="30" value="${type}">
                </c:when>
                <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
                    <textarea name="${sections}" cols="30" rows="10">
                            ${type.getBulletedList()}
                    </textarea>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="organization" items="${type.getOrganizations()}">
                        <dl>
                            <dt>Organization:</dt>
                            <dd><input type="text" name="${sections}" size="30"
                                       value="${organization.getHomePage().getOrganizationName()}"></dd>
                        </dl>
                        <dl>
                            <dt>web-site:</dt>
                            <dd><input type="text" name="${sections}_url" size="30"
                                       value="${organization.getHomePage().getUrl()}"></dd>
                        </dl>
                        <div>
                            <c:forEach var="position" items="${organization.getPositions()}">
                                <jsp:useBean id="positions"
                                             type="com.zahaand.webapp.model.Organization.Position"></jsp:useBean>
                                <dl>start date:</dl>
                                <dd><input type="text" name="${sections}_${sections.index}_startDate" size="10"
                                           value="${position.startDate}">
                                </dd>
                                <dl>end date:</dl>
                                <dd><input type="text" name="${sections}_${sections.index}_endDate" size="10"
                                           value="${position.endDate}">
                                </dd>
                                <dl>position:</dl>
                                <dd><input type="text" name="${sections}_${sections.index}_position" size="30"
                                           value="${position.position}">
                                </dd>
                                <dl>description:</dl>
                                <dd><textarea name="${sections}_${sections.index}_description" rows="5"
                                              cols="30">${position.description}</textarea>
                                </dd>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
        <hr/>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Back</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
</html>
