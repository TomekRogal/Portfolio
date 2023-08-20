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
    <jsp:include page="/resources/sharedviews/header.jsp"/>
    <section class="login-page">
    <div>
        <h2>
            Pomyślnie zmieniono hasło. <br> <a href="/login" class="btn btn--without-border" style="margin-top: 15px">Zaloguj się</a>
        </h2>
    </div>
    <div>

    </div>
    </section>
<jsp:include page="/resources/sharedviews/footer.jsp"/>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>