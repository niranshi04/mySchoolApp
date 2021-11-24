<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container marketing">

    <div class="container">  
            <div class="row my-lg-5 justify-content-center">
                <div class="feature-card p-lg-4 p-4 mx-4" onclick="location.href='/admin/studentRegister'">
                    <span class="fa fa-sign-in fa-3x" aria-hidden="true"></span>
                    <h3 class="my-3">Add Student</h3>
                    <p>Add a new Student</p>
                </div>
            </div> 
            <div class="row my-lg-5 justify-content-center">
                <div class="feature-card p-lg-4 p-4 mx-4" onclick="location.href='/admin/studentDetail'">
                    <span class="fa fa-sign-in fa-3x" aria-hidden="true"></span>
                    <h3 class="my-3">Edit Student</h3>
                    <p>Edit a Student Details</p>
                </div>
            </div> 
            <div class="row my-lg-5 justify-content-center">
                <div class="feature-card p-lg-4 p-4 mx-4" onclick="location.href='/admin/viewAllStudents'">
                    <span class="fa fa-id-badge fa-3x" aria-hidden="true"></span>
                    <h3 class="my-3">View Students</h3>
                    <p>View all the users</p>
                </div>
            </div>
            <div class="row my-lg-5 justify-content-center">
                <div class="feature-card p-lg-4 p-4 mx-4" onclick="location.href='/admin/viewFeesDetails'">
                    <span class="fa fa-id-badge fa-3x" aria-hidden="true"></span>
                    <h3 class="my-3">View Fees Details</h3>
                    <p>View Fees Details</p>
                </div>
            </div>
    </div>
</div>
