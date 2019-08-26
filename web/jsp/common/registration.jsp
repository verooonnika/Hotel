<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="page" value="/jsp/common/registration.jsp" scope="request"/>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : pageContext.request.locale}"
               scope="session"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="title.register"/></title>
</head>
<body>
<div>
    <c:import url="language-form.jsp"/>
</div>
<div>
    <c:import url="header.jsp"/>
</div>
<div>

    <!-- Registration form -->
    <form action="<c:url value="/controller"/>" method="post">
        <input type="hidden" name="command" value="register"/>
        <label for="loginField"><fmt:message key="title.login"/></label>
        <input type="text"
               name="login"
               value=""
               id="loginField" pattern="^[(\w)-]{4,20}" required=""
               placeholder="<fmt:message key = "placeholder.login"/>"/>

        <label for="passwordField"><fmt:message key="label.password"/></label>
        <input type="password"
               name="password"
               id="passwordField"
               pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+={};:><.,/?`~±§-])(?=[^\r\n\t\f\v]+$).{8,20}$"
               required=""
               placeholder="<fmt:message key = "placeholder.password"/>"/>

        <label for="emailField"><fmt:message key="label.email"/></label>
        <input type="email"
               name="email"
               value=""
               pattern="[-0-9a-zA-Z.+_]{2,64}+@[-0-9a-zA-Z.+_]+\.[a-zA-Z]{2,4}"
               id="emailField"
               required=""/>

        <label for="firstNameField"><fmt:message key="label.first_name"/></label>
        <input type="text"
               name="first-name"
               value=""
               id="firstNameField"
               pattern="[A-Za-zА-Яа-яЁё]{3,30}"
               required=""/>

        <label for="lastNameField"><fmt:message key="label.last_name"/></label>
        <input type="text"
               name="last-name"
               value=""
               id="lastNameField"
               pattern="[A-Za-zА-Яа-яЁё]{3,30}"
               required=""/>

        <label for="phoneNumberField"><fmt:message key="label.phone_number"/></label>
        <input type="text"
               name="phone-number"
               value=""
               id="phoneNumberField"
               pattern="[0-9]{7,20}"
               required=""/>

        <label for="countryField"><fmt:message key="label.country"/></label>
        <input type="text"
               name="country"
               value=""
               id="countryField"
               pattern="^[(\w)-]{4,25}"
               required=""/>

        <label for="birthdayField"><fmt:message key="label.birthday"/></label>
        <input type="date"
               name="birthday"
               value=""
               id="birthdayField"
               min="1920-01-01"
               required=""/>

        <input type="submit" value="<fmt:message key="button.register"/> ">
    </form>
</div>

</body>
</html>