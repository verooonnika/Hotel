<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="page" value="/jsp/user/book-room.jsp" scope="request"/>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : pageContext.request.locale}"
               scope="session"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <c:import url="../common/language-form.jsp"/>
</div>
<div>
    <c:import url="../common/header.jsp"/>
</div>
<!-- Book Room form -->
<form action="<c:url value="/controller"/>" method="post">
    <input type = "hidden" name = "command" value = "book-room"/>
    <input type="hidden" name="room" value="${requestScope.room}"/>

    <label><fmt:message key="label.arrival"/></label>
    <input type="hidden" name="arrival" value="${sessionScope.arrival}"/>
    <c:out value="${sessionScope.arrival}"/>

    <label><fmt:message key="label.departure"/></label>
    <input type ="hidden" name="departure" value = "${sessionScope.departure}"/>
    <c:out value="${sessionScope.departure}"/>

    <label for="numberGuestsField"><fmt:message key="label.number_guests"/></label>
    <input type="number"
           name="number-of-guests"
           value=""
           id="numberGuestsField"
           pattern="[0-9]{1,5}"
           required=""/>

    <label for="guestsNamesField"><fmt:message key="label.guests_names"/></label>
    <input type="text"
           name="guests-names"
           value=""
           id="guestsNamesField"
           pattern="^[(\w)-]{4,60}"
           required=""/>

    <input type="submit" value="<fmt:message key="button.book"/> ">
</form>
</body>
</html>

