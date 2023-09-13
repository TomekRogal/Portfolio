<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="container container--70">

    <ul class="nav--actions">
        <li class="logged-user">
            Witaj ${loggedUser.username}
            <ul class="dropdown">
                <li><a href="#">Profil</a></li>
                <li><a href="/donation/all">Moje zbiórki</a></li>
                <li><a href="/donation">Aplikacja</a></li>
                <sec:authorize access="hasRole('ADMIN')">
                    <li><a href="/admin">Panel admin</a></li>
                </sec:authorize>
                <li>
                    <a>
                        <form action="<c:url value="/logout"/>" method="post">
                            <input class="btn--small" type="submit" value="Wyloguj">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </a>
                </li>
            </ul>
        </li>
    </ul>
    <ul>
        <a href="/admin" style="float: left" class="btn btn--without-border active">Panel Administratora</a>
        <li><a href="/" class="btn btn--without-border active">Start</a></li>
    </ul>
</nav>