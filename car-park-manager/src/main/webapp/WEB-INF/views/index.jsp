<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
        <meta>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spring MVC</title>
        </head>
    <body>
        <a href="?mylocale=en">English</a> | <a href="?mylocale=ru">Russian</a>
        <h4> <spring:message code="entities.title"/></h4>
        <h4> <spring:message code="entities.message"/></h4>
        <span class="blue">${msg}</span>
    </body>
</html>
