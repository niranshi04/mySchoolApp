<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-fluid custom-container">
    <div class="table-responsive mt-2">
    <form:form method="post" action="/admin/postAttendance" modelAttribute="atlist">
        <table class="table table-hover mt-4 table-sort">
            <thead>
                <tr>
                    <th>Registration No</th>
                    <th>Name</th>
                    <th>Attendance</th>
                </tr>
                
            </thead>
            <tbody>
            <c:forEach items="${atlist.student}" var="at" varStatus="status">
                <tr>
                    <td>${at.classStudent.student.registrationNo}</td>
                    <td>${at.classStudent.student.name}</td>
                    <td>
                    <form:input type="text" path="student[${status.index}].classStudent.student.registrationNo" class="form-control" required="true" readonly="true" hidden="true"></form:input>
                    <form:input type="text" path="student[${status.index}].classDetails.classId" class="form-control" required="true" readonly="true"  hidden="true" value="${attendance.classDetails.classId }"></form:input>
                     <form:input type="date" path="student[${status.index}].date" class="form-control" required="true" readonly="true" hidden="true"></form:input>
                    Present <form:radiobutton path="student[${status.index}].attendance" value="1"/>  
        			Absent <form:radiobutton path="student[${status.index}].attendance" value="0"/>  
                    </td>
                    
                </tr>
            </c:forEach>
            </tbody>
        </table>
         <div class="form-group">
                                    <div class="input-group mt-2">
                                        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
                                    </div>
                                </div>
        </form:form>
    </div>
</div>

