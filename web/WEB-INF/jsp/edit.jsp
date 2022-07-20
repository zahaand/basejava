<%@ page import="com.zahaand.webapp.util.DateUtil" %>
<%@ page import="com.zahaand.webapp.model.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" scope="request" type="com.zahaand.webapp.model.Resume"/>
    <title>Edit resume ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"></jsp:include>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <h1>Name:</h1>
        <dl>
            <input class="field" type="text" name="fullName" size=55 placeholder="ФИО"
                   value="${resume.fullName}" required>
        </dl>
        <h2>Contacts:</h2>
        <c:forEach var="contactType" items="${ContactType.values()}">
            <dl>
                <dt>${contactType.title}</dt>
                <dd><input type="text" name="${contactType}" size="30" value="${resume.getContact(contactType)}"></dd>
            </dl>
        </c:forEach>
        <hr>
        <c:forEach var="sectionType" items="${SectionType.values()}" varStatus="sectionTypeCounter">
            <c:choose>
                <c:when test="${resume.sections.size() != 0}">
                    <c:set var="sectionValue" value="${resume.getSection(sectionType)}"></c:set>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${sectionTypeCounter.index == 0 || sectionTypeCounter.index == 1}">
                            <c:set var="sectionValue" value="<%=new TextSection()%>"></c:set>
                        </c:when>
                        <c:when test="${sectionTypeCounter.index == 2 || sectionTypeCounter.index == 3}">
                            <c:set var="sectionValue" value="<%=new ListSection(new ArrayList<>())%>"></c:set>
                        </c:when>
                        <c:when test="${sectionTypeCounter.index == 4 || sectionTypeCounter.index == 5}">
                            <c:set var="sectionValue" value="<%=new OrganizationSection(new ArrayList<>())%>"></c:set>
                        </c:when>
                    </c:choose>
                </c:otherwise>
            </c:choose>
            <jsp:useBean id="sectionValue" type="com.zahaand.webapp.model.AbstractSection"></jsp:useBean>
            <h2><a>${sectionType.title}</a></h2>
            <c:choose>
                <c:when test="${sectionType == 'OBJECTIVE' || sectionType == 'PERSONAL'}">
                    <textarea name="${sectionType.title}" cols="75" rows="5">
                        <%=String.valueOf(sectionValue)%>
                    </textarea>
                </c:when>
                <c:when test="${sectionType == 'ACHIEVEMENT' || sectionType == 'QUALIFICATIONS'}">
                    <textarea name="${sectionType.title}" cols="75" rows="5">
                        <%=String.join("\n", ((ListSection) sectionValue).getBulletedList())%>
                    </textarea>
                </c:when>
                <c:when test="${sectionType == 'EXPERIENCE' || sectionType == 'EDUCATION'}">
                    <c:choose>
                        <c:when test="<%=((OrganizationSection) sectionValue).getOrganizations().size() != 0%>">
                            <c:forEach var="organization"
                                       items="<%=((OrganizationSection) sectionValue).getOrganizations()%>"
                                       varStatus="organizationCounter">
                                <dl>
                                    <dt>Organization:</dt>
                                    <dd><input type="text" name="${sectionType.title}" size="30"
                                               value="${organization.homePage.organizationName}"></dd>
                                </dl>
                                <dl>
                                    <dt>Web-site:</dt>
                                    <dd><input type="text" name="url${sectionType.title}" size="30"
                                               value="${organization.homePage.url}"></dd>
                                </dl>
                                <div style="margin-left: 30px">
                                    <c:forEach var="position" items="${organization.positions}">
                                        <jsp:useBean id="position"
                                                     type="com.zahaand.webapp.model.Organization.Position"></jsp:useBean>
                                        <dl>
                                            <dt>Start date:</dt>
                                            <dd><input type="text"
                                                       name="startDate${organization.homePage.organizationName}${organizationCounter.index}"
                                                       size="10"
                                                       value="<%=DateUtil.format(position.getStartDate())%>">
                                            </dd>
                                        </dl>
                                        <dl>
                                            <dt>End date:</dt>
                                            <dd><input type="text"
                                                       name="endDate${organization.homePage.organizationName}${organizationCounter.index}"
                                                       size="10"
                                                       value="<%=DateUtil.format(position.getEndDate())%>">
                                            </dd>
                                        </dl>
                                        <dl>
                                            <dt>Position:</dt>
                                            <dd><input type="text"
                                                       name="position${organization.homePage.organizationName}${organizationCounter.index}"
                                                       size="67"
                                                       value="${position.position}">
                                            </dd>
                                        </dl>
                                        <dl>
                                            <dt>Description:</dt>
                                            <dd><textarea
                                                    name="description${organization.homePage.organizationName}${organizationCounter.index}"
                                                    rows="5"
                                                    cols="65">${position.description}</textarea>
                                            </dd>
                                        </dl>
                                    </c:forEach>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <dl>
                                <dt>Organization:</dt>
                                <dd><input type="text" name="${sectionType.title}" size="30"></dd>
                            </dl>
                            <dl>
                                <dt>Web-site:</dt>
                                <dd><input type="text" name="url${sectionType.title}" size="30"></dd>
                            </dl>
                            <div style="margin-left: 30px">
                                <dl>
                                    <dt>Start date:</dt>
                                    <dd><input type="text"
                                               name="startDate${organization.homePage.organizationName}${organizationCounter.index}"
                                               size="10">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>End date:</dt>
                                    <dd><input type="text"
                                               name="endDate${organization.homePage.organizationName}${organizationCounter.index}"
                                               size="10"">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>Position:</dt>
                                    <dd><input type="text"
                                               name="position${organization.homePage.organizationName}${organizationCounter.index}"
                                               size="67"">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>Description:</dt>
                                    <dd><textarea
                                            name="description${organization.homePage.organizationName}${organizationCounter.index}"
                                            rows="5"
                                            cols="65"></textarea>
                                    </dd>
                                </dl>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:when>
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