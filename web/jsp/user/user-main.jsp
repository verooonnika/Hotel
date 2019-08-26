<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="page" value="/jsp/user/user-main.jsp" scope="request"/>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : pageContext.request.locale}"
               scope="session"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title></title>
</head>
<body>
<div>
    <c:import url="../common/language-form.jsp"/>
</div>
<div>
    <c:import url="../common/header.jsp"/>
</div>
<div>
    <h3><fmt:message key="label.welcome"/>${sessionScope.user.firstName}</h3>
</div>

<div>
    <form action="<c:url value="/controller"/>" method="post">
        <input type="hidden" name="command" value="bookings"/>
        <input type="submit" name="submit" value="<fmt:message key="button.bookings"/> "/>
    </form>
</div>
<div>${sessionScope.errorFindingBookings}</div>

<div>
    <form action="<c:url value="/controller"/>" method="post">
        <input type="hidden" name="command" value="logout"/>
        <input type="submit" name="submit" value="<fmt:message key="button.logout"/> "/>
    </form>
</div>
<jsp:useBean id="date" class="java.util.Date" />
<div>
    <form action="<c:url value="/controller"/>" method="post">
        <input type="hidden" name="command" value="check-dates"/>
        <label for="arrivalDateField"><fmt:message key="label.arrival"/></label>
        <input type="date"
               name="arrival"
               value=""
               min = "<fmt:formatDate value="${date}" pattern="yyyy-MM-dd" />"
               id="arrivalDateField"
               required=""/>

        <label for="departureField"><fmt:message key="label.departure"/></label>
        <input type="date"
               name="departure"
               id="departureField"
               min = "<fmt:formatDate value="${date}" pattern="yyyy-MM-dd" />"
               required=""/>
        <input type="submit" value="<fmt:message key="button.check_dates"/>"/>
    </form>
</div>

</body>
</html>