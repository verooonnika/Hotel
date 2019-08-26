<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
<form action="<c:url value="/controller"/>" method="get">
    <input type = "hidden" name = "command" value = "change-language">
    <input type = "hidden" name = "page" value = "${requestScope.page}">
    <select name = "locale" onchange="submit()">
        <option selected = "selected" value="${ not empty sessionScope.locale ? sessionScope.locale : pageContext.request.locale }">
            <c:choose>
                <c:when test = "${ sessionScope.locale eq 'en_us' }">
                    <c:out value = "English"/>
                </c:when>
                <c:when test="${ sessionScope.locale eq 'ru_ru' }">
                    <c:out value = "Русский"/>
                </c:when>
                <c:when test = "${ sessionScope.locale eq 'ru_by' }">
                    <c:out value = "Беларускі"/>
                </c:when>
                <c:otherwise>
                    <c:out value = "Locale"/>
                </c:otherwise>
            </c:choose>
        </option>
        <option value="en_us">EN</option>
        <option value="ru_ru">RU</option>
        <option value="ru_by">BY</option>
    </select>
</form>
</body>
</html>