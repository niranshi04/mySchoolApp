<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container marketing">

    <div class="container">  
            <div class="row my-lg-5 justify-content-center">
                <div class="feature-card p-lg-4 p-4 mx-4" onclick="location.href='/admin/deleteTeacher/${teacher.teacherId}'">
                    <span class="fa fa-sign-in fa-3x" aria-hidden="true"></span>
                    <h3 class="my-3">Delete</h3>
                    <p>Delete teacher</p>
                </div>
            </div> 
            <div class="row my-lg-5 justify-content-center">
                <div class="feature-card p-lg-4 p-4 mx-4" onclick="location.href='/admin/editTeacher/${teacher.teacherId}'">
                    <span class="fa fa-sign-in fa-3x" aria-hidden="true"></span>
                    <h3 class="my-3">Edit Teacher</h3>
                    <p>Edit Teacher Details</p>
                </div>
            </div> 
            
    </div>
</div>
