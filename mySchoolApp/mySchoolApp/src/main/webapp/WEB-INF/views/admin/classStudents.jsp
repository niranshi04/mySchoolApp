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
                    <th>DOB</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${classStudents}" var="classStudent1">
                <tr>
                    <td>${classStudent1.student.registrationNo}</td>
       <td>${classStudent1.student.name}</td>
       <td>${classStudent1.student.dateOfBirth}</td>
       <td>
       <form:form class="" action="/admin/deleteClassStudent" method="post" modelAttribute="classStudent">
               
                  
                        <form:input type="text" path="classDetails.classId" class="form-control" required="true" readonly="true" hidden = "true" value="${classStudent.classDetails.classId}"></form:input>
                     
              
                        <form:input type="text" path="startYear" class="form-control" required="true" readonly="true" hidden = "true" value="${classStudent.startYear}"></form:input>
                     
                        <form:input type="text" path="student.registrationNo" class="form-control" required="true" readonly="true"  hidden = "true" value="${classStudent1.student.registrationNo}"></form:input>
                       
    
                
                   
                        <button class="btn btn-primary" type="submit" name= "submit" value="1" >Delete</button>
                    
            </form:form>
       </td>
                    
                   
                </tr>
            </c:forEach>
            </tbody>
            </table>
            <div class="row shadow bg-white rounded" style="border: 1px solid whitesmoke; padding: 0 40px;">
        <table class="table table-borderless mt-4">
            <form:form class="form-horizontal" action="/admin/addClassStudent" method="post" modelAttribute="classStudent">
                <tr>
                    <th style="width: 40%;">
                        <h4>Add Student in Class</h4>
                    </th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%; text-align: right;">
                        <a href="#" onclick="window.location.reload();">Reset <i class="fa fa-refresh"
                                aria-hidden="true"></i></a>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Class Id</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="classDetails.classId" class="form-control" required="true" readonly="true"></form:input>
                     </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Start year</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="startYear" class="form-control" required="true" readonly="true"></form:input>
                     </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Registration No ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="student.registrationNo" class="form-control" required="true"></form:input>
                        <form:errors path="student.registrationNo" style="color: red;"></form:errors>
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
          <table class="table table-hover mt-4 table-sort">
            <thead>
                <tr>
                    <th>Subject Name</th>
                    <th>Teacher Name </th>
                   <th>Delete</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${teacherClassSubjects}" var="teacherClassSubject">
                <tr>
                    <td>${teacherClassSubject.subject.subjectName}</td>
       <td>${teacherClassSubject.teacher.name}</td>
      <td>
       <form:form class="" action="/admin/deleteTeacherClassSubject" method="post" modelAttribute="teacherClassSubject">
                
                        <form:input type="text" path="classDetails.classId" class="form-control" required="true" readonly="true" hidden = "true" value="${classStudent.classDetails.classId}"></form:input>
                   
                
                        <form:input type="text" path="year" class="form-control" required="true" readonly="true" hidden = "true"  value="${classStudent.startYear}"></form:input>
                    
                        <form:input type="text" path="subject.subjectId" class="form-control" required="true" hidden = "true"  readOnly="true" value="${teacherClassSubject.subject.subjectId}"></form:input>
                      
    
                        <button class="btn btn-primary" type="submit" name= "submit" value="1" >Delete</button>
                    
            </form:form> 
            </td>   
                </tr>
            </c:forEach>
            </tbody>
            </table>
            
         
        <table class="table table-borderless mt-4">
            <form:form class="form-horizontal" action="/admin/addEditTeacherClassSubject" method="post" modelAttribute="teacherClassSubject">
                <tr>
                    <th style="width: 40%;">
                        <h4>Add or Edit Subject Teacher</h4>
                    </th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%; text-align: right;">
                        <a href="#" onclick="window.location.reload();">Reset <i class="fa fa-refresh"
                                aria-hidden="true"></i></a>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Class Id</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="classDetails.classId" class="form-control" required="true" readonly="true"></form:input>
                     </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Year</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="year" class="form-control" required="true" readonly="true"></form:input>
                     </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Subject Id ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="subject.subjectId" class="form-control" required="true"></form:input>
                        <form:errors path="subject.subjectId" style="color: red;"></form:errors>
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
</div>

