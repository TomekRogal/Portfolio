<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
<jsp:include page="/resources/sharedviews/header.jsp"/>

<section class="login-page">
    <h2>Załóż konto</h2>
<form:form method="post"
           modelAttribute="user">
    <form:hidden path="id"/>
        <div class="form-group">
            <form:input path="email"  type="email" name="email" placeholder="Email" />
            <form:input path="username"  style="float: right" type="text" name="username" placeholder="Nazwa użytkownika" />
            <c:if test='${register.equals("failed")}'>
                <p class="error"> Użytkownik o podanej nazwie lub emailu już istnieje</p>
            </c:if>
        </div>
        <div class="form-group">
            <form:input path="password" type="password" name="password" placeholder="Hasło" />
            <input style="float: right" type="password" name="password2" placeholder="Powtórz hasło" />
            <c:if test='${pass.equals("failed")}'>
                <p class="error"> Hasła muszą być takie same</p>
            </c:if>
        </div>
        <div class="form-group">
            <form:input path="firstName" type="text" name="firstName" placeholder="Imię" />
            <form:input path="lastName"  style="float: right" type="text" name="lastName" placeholder="Nazwisko" />
        </div>
        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
</form:form>
</section>
<jsp:include page="/resources/sharedviews/footer.jsp"/>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
