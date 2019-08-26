<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="page" value="/jsp/commom/available-rooms.jsp" scope="request"/>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : pageContext.request.locale}"
               scope="session"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title></title>
</head>
<body>
<div>
    <c:import url="language-form.jsp"/>
</div>
<div>
    <c:import url="header.jsp"/>
</div>
<table>
    <c:forEach var="room" items="${requestScope.rooms}">

        <tr>
            <td>${room.number}</td>
            <td>${room.getRoomType()}</td>
            <td>${room.sleeps}</td>
            <td>${room.cost}</td>
            <td>
                <form action="<c:url value="/controller"/>" method="post">
                    <input type="hidden" name="command" value="to-booking"/>
                    <input type="hidden" name="room" value="${room.number}"/>
                    <input type="submit" value="<fmt:message key="button.book"/>"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
