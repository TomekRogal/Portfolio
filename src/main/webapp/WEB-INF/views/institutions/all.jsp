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

<section class="table-sub-heading-color">
    <h2>Instytucje:</h2>
    <div class="table-tab">
        <table id="tableinstitutions" class="table" >
            <thead>
            <tr>
                <th>Nazwa</th>
                <th>Opis</th>
                <th>Akcje</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${institutions}" var="institution">
                <tr>
                    <td>${institution.name}</td>
                    <td>${institution.description}</td>
                    <td>
                        <a href="/admin/institution/edit/${institution.id}">
                            <button type="button" class="btn btn--small">Edytuj</button>
                        </a>
                        <a href="/admin/institution/delete/${institution.id}" class="delete-link">
                            <button type="button" class="btn btn--small">Usuń</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="/admin/institution/add">
        <button style="margin-right:30px;float:right" type="button" class="btn">Dodaj</button>
        </a>
    </div>
</section>
</body>
</html>