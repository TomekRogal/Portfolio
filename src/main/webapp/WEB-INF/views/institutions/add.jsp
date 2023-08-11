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
    <h2>Dodaj instytucję:</h2>
    <div class="form">
        <form:form method="post"
                   modelAttribute="institution" cssStyle="text-align: center">
        <form:hidden path="id"/>
            <div class="form-group">
                <form:input path="name"  type="text" name="name" placeholder="Nazwa" />
            </div>
            <div class="form-group">
                <form:textarea path="description"  type="text" name="description" placeholder="Opis" rows="10"/>
            </div>
            <div class="form-group">
            <input class="btn" type="submit" value="Dodaj">
            </div>
        </form:form>
        <a href="/admin/institution/all">
            <button style="margin-right:30px;float:right" type="button" class="btn">Lista instytucji</button>
        </a>
    </div>
</section>
</body>
</html>