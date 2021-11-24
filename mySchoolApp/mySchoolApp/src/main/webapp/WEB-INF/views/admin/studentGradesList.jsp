<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-fluid custom-container">
    <div class="table-responsive mt-2">
        <table class="table table-hover mt-4 table-sort">
            <thead>
                <tr>
                    <th>RegistrationNo</th>
                    <th>Student Name</th>
                    <th>Exam Name</th>
                    <th>Teacher name</th>
                    <th>Grades</th>
                    
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${gradeList}" var="glist">
                <tr>
                    <td>${glist.classStudent.student.registrationNo}</td>
       <td>${glist.classStudent.student.name}</td>
       <td>${glist.exam.examName}</td>
       <td>${glist.teacher.name}"
       <td>${glist.grades}</td>
      
                    
                   
                </tr>
            </c:forEach>
            </tbody>
            </table>
            <table class="table table-borderless mt-4">
            <form:form class="form-horizontal" action="/admin/addEditStudentGrades" method="post" modelAttribute="grades">
                <tr>
                    <th style="width: 40%;">
                        <h4>Add or Edit Grades</h4>
                    </th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%; text-align: right;">
                        <a href="#" onclick="window.location.reload();">Reset <i class="fa fa-refresh"
                                aria-hidden="true"></i></a>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">RegistrationNo</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="classStudent.student.registrationNo" class="form-control" required="true" readonly="true"></form:input>
                     </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">ClassId</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="classStudent.classDetails.classId" class="form-control" required="true" readonly="true"></form:input>
                     </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">year</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="classStudent.startYear" class="form-control" required="true" readonly="true"></form:input>
                     </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Subject Id </th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="subject.subjectId" class="form-control" required="true" readonly="true"></form:input>
                        <form:errors path="subject.subjectId" style="color: red;"></form:errors>
                     </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Exam Id ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="exam.examId" class="form-control" required="true" ></form:input>
                        <form:errors path="exam.examId" style="color: red;"></form:errors>
                     </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Grade ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="grades" class="form-control" required="true" ></form:input>
                        <form:errors path="grades" style="color: red;"></form:errors>
                     </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Teacher Id ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="teacher.teacherId" class="form-control" required="true" ></form:input>
                        <form:errors path="teacher.teacherId" style="color: red;"></form:errors>
                     </td>
                </tr>
               
                <tr>
                    <th style="width: 40%; text-align: center;"></th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <button class="btn btn-primary" type="submit" name= "submit" value="1" >submit</button>
                    </td>
                </tr>   
            </form:form>

        </table>
   
          
</div>

