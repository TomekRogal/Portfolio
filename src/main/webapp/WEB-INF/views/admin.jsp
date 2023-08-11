<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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

<section class="steps">
    <h2>Zarządzanie:</h2>
    <a href="/admin/institution/all" class="btn btn--large">Instytucje</a><br>
    <a href="/admin/users/all" class="btn btn--large">Użytkownicy</a><br>
    <a href="/admin/admins/all" class="btn btn--large">Administratorzy</a><br>

</section>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>