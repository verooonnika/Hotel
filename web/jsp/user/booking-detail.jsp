<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="page" value="/jsp/user/booking-detail.jsp" scope="request"/>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : pageContext.request.locale}"
               scope="session"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title><fmt:message key="label.bookings"/></title>
</head>
<body>
<div>
    <c:import url="../common/language-form.jsp"/>
</div>
<div>
    <c:import url="../common/header.jsp"/>
</div>
<div>
    <c:out value="${requestScope.booking.bookingId}"/>
    <c:out value="${requestScope.booking.arrival}"/>
    <c:out value="${requestScope.booking.departure}"/>
    <c:out value="${requestScope.booking.room.getRoomType()}"/>
    <c:out value="${requestScope.booking.guestsNumber}"/>
    <c:out value="${requestScope.booking.guestName}"/>
</div>
<div>
    <form action = "<c:url value="/controller"/>" method="post">
        <input type="hidden" name="command" value="cancel-booking">
        <input type="hidden" name="booking-id" value="${ requestScope.booking.bookingId}">
        <input type="submit" name="submit"
               value="<fmt:message key = "button.cancel_booking"/>">
    </form>
</div>


</body>
</html>
