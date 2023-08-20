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
    <jsp:include page="/resources/sharedviews/appheader.jsp"/>
</header>

<section class="steps">
    <div class="slogan container container--90">
        <h2>500</h2>
        <p>Wewnętrzny błąd serwera.</p>
    </div>
    <a href="/" class="btn btn--large">

        Wróć do strony głównej
    </a>

</section>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>