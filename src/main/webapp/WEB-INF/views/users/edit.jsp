<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <jsp:include page="/resources/sharedviews/adminheader.jsp"/>
</header>

<section class="form-section">
    <h2>Edytuj dane administratora:</h2>
    <div class="form">
        <form:form method="post"
                   modelAttribute="user" cssStyle="text-align: center">
            <form:hidden path="id"/>
            <form:hidden path="password"/>
            <form:hidden path="roles"/>
            <form:hidden path="enabled"/>
            <div class="form-group">
                <form:input path="username"  type="text" name="username" placeholder="Nazwa użytkownika" />
            </div>
            <div class="form-group">
                <form:input path="firstName"  type="text" name="firstName" placeholder="Imię"/>
            </div>
            <div class="form-group">
                <form:input path="lastName"  type="text" name="lastName" placeholder="Nazwisko"/>
            </div>
            <div class="form-group">
                <form:input path="email"  type="email" name="email" placeholder="Email"/>
            </div>
            <div class="form-group">
                <input class="btn" type="submit" value="Edytuj">
            </div>
        </form:form>
        <a href="/admin/user/all">
            <button style="margin-right:30px;float:right" type="button" class="btn">Lista użytkowników</button>
        </a>
    </div>
</section>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>