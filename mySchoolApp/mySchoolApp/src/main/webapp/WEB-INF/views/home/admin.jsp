<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container">
    <div class="row my-lg-5 justify-content-center">
        <div class="feature-card p-lg-4 p-4 mx-4" onclick="location.href='/user'">
            <span class="fa fa-users fa-3x" aria-hidden="true"></span>
            <h3 class="my-3">USERS PORTAL</h3>
            <p>Manage all users.</p>
        </div>
        <div class="feature-card p-lg-4 p-4 mx-4" onclick="location.href='/admin/students'">
            <span class="fa fa-user-graduate fa-3x" aria-hidden="true"></span>
            <h3 class="my-3">STUDENT PORTAL</h3>
            <p>Manage all the students.</p>
        </div>
    </div>
    <div class="row my-lg-5 justify-content-center">
    	<div class="feature-card p-lg-4 p-4 mx-4" onclick="location.href='/admin/teacher'">
            <span class="fa fa-clipboard-list fa-3x" aria-hidden="true"></span>
            <h3 class="my-3">TEACHER PORTAL</h3>
            <p>Manage all the Teachers</p>
        </div>
        <div class="feature-card p-lg-4 p-4 mx-4" onclick="location.href='/admin/classes'">
            <span class="fa fa-user-tie fa-3x" aria-hidden="true"></span>
            <h3 class="my-3">CLASS PORTAL</h3>
            <p>Manage all the Classes.</p>
        </div>
    </div>
    <div class="row my-lg-5 justify-content-center">
    	<div class="feature-card p-lg-4 p-4 mx-4" onclick="location.href='/admin/attendance'">
            <span class="fa fa-money-check fa-3x" aria-hidden="true"></span>
            <h3 class="my-3">Attendance</h3>
            <p>Manage the Attendance of the Students.</p>
        </div>
        <div class="feature-card p-lg-4 p-4 mx-4" onclick="location.href='/admin/studentGrades'">
            <span class="fa fa-money-check fa-3x" aria-hidden="true"></span>
            <h3 class="my-3">Grades</h3>
            <p>Manage the Grades of the Students.</p>
        </div>
    </div>
</div>
