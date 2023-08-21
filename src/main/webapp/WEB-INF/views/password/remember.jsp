<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
    <h2>Podaj adres email:</h2>
    <form:form method="post">
        <div class="form-group">
            <input type="email" name="email" placeholder="Email" />
        </div>

    <div class="form-group form-group--buttons">
        <button class="btn" type="submit">Wyślij</button>
    </div>
        <c:if test='${email.equals("failed")}'>
            <p class="error"> Użytkownik o podanym adresie email nie istnieje.</p>
        </c:if>
    </form:form>
</section>
<jsp:include page="/resources/sharedviews/footer.jsp"/>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
