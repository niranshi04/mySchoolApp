<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-fluid custom-container">
    <div class="table-responsive mt-2">
        <table class="table table-hover mt-4 table-sort">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email Address</th>
                    <th>Registration Number</th>
                    <th>Gender</th>
                    <th>DOB</th>
                    <th>House Number</th>
                    <th>Street</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Date of Admission</th>
                    <th>Date of Leaving School</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${students}" var="student">
                <tr>
                    <td>${student.name}</td>
                    <td>${student.user.emailAddress}</td>
                    <td>${student.registrationNo}</td>
                    <td>${student.gender}</td>
                    <td>
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${student.dateOfBirth}" />
                    </td>
                   <td>${student.houseNumber}</td>
                   <td>${student.street}</td>
                   <td>${student.city}</td>
                   <td>${student.state}</td>
                   <td>
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${student.dateOfAdmission}" />
                    </td>
                    <td>
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${student.dateOfLeavingSchool}" />
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

