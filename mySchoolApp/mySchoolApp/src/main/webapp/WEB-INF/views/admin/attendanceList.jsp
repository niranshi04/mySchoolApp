<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-fluid custom-container">
    <div class="table-responsive mt-2">
        
            <div class="row shadow bg-white rounded" style="border: 1px solid whitesmoke; padding: 0 40px;">
        <table class="table table-borderless mt-4">
            <form:form class="form-horizontal" action="/admin/takeAttendance" method="post" modelAttribute="attendance">
                <tr>
                    <th style="width: 40%;">
                        <h4>Take Attendance</h4>
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
                    <th style="width: 40%; text-align: center;">Date</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="date" path="date" class="form-control" required="true"></form:input>
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

