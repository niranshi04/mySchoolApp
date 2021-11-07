<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="today" value="<%= new java.util.Date()%>" />
<fmt:formatDate var="todayFormatted" value="${today}" pattern="yyyy-MM-dd" />
<c:set var="role">${pageContext.request.userPrincipal.principal.user.role}</c:set>
<c:set var="mandatory"><span style="color: red;">*</span></c:set>
<c:set var="rolecolor">
    <c:choose>
       <c:when test="${role == 'admin'}">#8B0000</c:when>
        <c:when test="${role == 'student'}">#006400</c:when>
        <c:when test="${role == 'teacher'}">#00008B</c:when>
        <c:otherwise>#343a40</c:otherwise>
    </c:choose>
</c:set>

<!doctype html>
<html lang="en" style="height: 100%;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Niranshi Agarwal">
    <title>${title}</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://kit.fontawesome.com/a22cb468b2.js" crossorigin="anonymous"></script>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/carousel.css" rel="stylesheet">
</head>

<body style="height: 100%; display: flex; flex-direction: column;">
    <header>
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark" style="background-color: ${rolecolor}!important;">
            <div class="container-fluid">
                <a class="navbar-brand mr-5" href="/">mySchoolApp</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                    aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse ml-2" id="navbarCollapse">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item mr-2">
                            <a class="nav-link active" href="/">Home <span class="sr-only">(current)</span></a>
                        </li>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li class="nav-item mr-2">
                                <a class="nav-link active" href="/user/register">Register</a>
                            </li>
                            <li class="nav-item mr-2">
                                <a class="nav-link active" href="/user/logout">Logout</a>
                            </li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_STUDENT')">
                            <li class="nav-item mr-2">
                                <a class="nav-link active" href="/user/logout">Logout</a>
                            </li>
                            
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_TEACHER')">
                            <li class="nav-item mr-2">
                                <a class="nav-link active" href="/user/logout">Logout</a>
                            </li>
                        </sec:authorize>
                    </ul>
                    <form class="form-inline mt-2 mt-md-0">
                        <c:choose>
                            <c:when test="${not empty pageContext.request.userPrincipal}">
                                <ul class="navbar-nav mr-2">
                                    <li class="nav-item mr-2 pr-2">
                                        <div class="nav-link active">Hi ${pageContext.request.userPrincipal.principal.user.emailAddress}!</div>
                                    </li>
                                    <li class="nav-item dropdown mr-5">
                                        <a class="nav-link active dropdown-toggle" href="#" id="userdropdown" aria-haspopup="true" aria-expanded="false">
                                            User: <c:out value="${pageContext.request.userPrincipal.principal.user.emailAddress}" />
                                        </a>
                                        <div class="dropdown-menu" aria-labelledby="userdropdown" id="userdropdownmenu">
                                            <a class="dropdown-item" href="/profile">View Profile</a>
                                            <a class="dropdown-item" href="/profile/change-password">Change Password</a>
                                            <a class="dropdown-item" href="/user/logout">Logout</a>
                                        </div>
                                    </li>
                                </ul>
                            </c:when>
                        </c:choose>
                    </form>
                </div>
            </div>
        </nav>
    </header>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb" style="margin-bottom: 0;">
        </ol>
    </nav>

    <div class="jumbotron" style="padding: 2rem 2rem;">
        <div class="container">
            <h4>${title}</h4>
            <h4>${error} ${status}</h4>
            <div>${message}</div>
            <div>${timestamp}</div>
        </div>
    </div>
