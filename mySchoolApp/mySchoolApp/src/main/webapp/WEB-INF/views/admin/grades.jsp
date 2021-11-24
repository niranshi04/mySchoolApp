<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container marketing">

    <div class="container">  
            <div class="row my-lg-5 justify-content-center">
                <div class="feature-card p-lg-4 p-4 mx-4" onclick="location.href='/admin/classGrades'">
                    <span class="fa fa-sign-in fa-3x" aria-hidden="true"></span>
                    <h3 class="my-3">Class</h3>
                    <p>Add or View grades of class</p>
                </div>
            </div> 
            <div class="row my-lg-5 justify-content-center">
                <div class="feature-card p-lg-4 p-4 mx-4" onclick="location.href='/admin/studentGrades'">
                    <span class="fa fa-sign-in fa-3x" aria-hidden="true"></span>
                    <h3 class="my-3">Student</h3>
                    <p>Add or View grades of individual</p>
                </div>
            </div> 
            
           
    </div>
</div>
