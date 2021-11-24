<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container marketing">

    <div class="container">
        <c:if test="${empty pageContext.request.userPrincipal}">    
            <div class="row my-lg-5 justify-content-center">
                <div class="feature-card p-lg-4 p-4 mx-4" onclick="location.href='/user/login'">
                    <span class="fa fa-sign-in fa-3x" aria-hidden="true"></span>
                    <h3 class="my-3">LOGIN</h3>
                    <p>Login to your account.</p>
                </div>
            </div> 
        </c:if>  
        <c:if test="${not empty pageContext.request.userPrincipal}">
            <div class="row my-lg-5 justify-content-center">
                <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF', 'ROLE_STUDENT', 'ROLE_TEACHER')">
                    <div class="feature-card p-lg-4 p-4 mx-4" onclick="location.href='/admin'">
                        <span class="fa fa-id-card fa-3x" aria-hidden="true"></span>
                        <h3 class="my-3">${fn:toUpperCase(role)} DASHBOARD</h3>
                        <p>Go to your dashboard</p>
                    </div>
                </sec:authorize>
            </div>
            <c:if test="${role == 'admin'}"></c:if>
            <c:if test="${role == 'staff'}"></c:if>
            <c:if test="${role == 'teacher'}"></c:if>
            <c:if test="${role == 'student'}"></c:if>
        </c:if>
t    </div>
</div>
