<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container" style="padding-left: 5%; padding-right: 5%;">
    <div class="row justify-content-center mb-3">
        <h2>${submessage1}</h2>
    </div>
    <div class="row shadow bg-white rounded" style="border: 1px solid whitesmoke; padding: 0 40px;">
        <table class="table table-borderless mt-4">
            <form:form class="form-horizontal" action="${submiturl}"
                method="post" modelAttribute="teacherSalary">
                <tr>
                    <th style="width: 40%;">
                        <h4>Step 2: Teacher Salary Details</h4>
                    </th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%; text-align: right;">
                        <a href="#" onclick="window.location.reload();">Reset <i class="fa fa-refresh"
                                aria-hidden="true"></i></a>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">TeacherId</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="teacherId" class="form-control" required="true" readonly="true"></form:input>
                     </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Salary Last Paid till</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="date" path="lastDate" class="form-control"></form:input>
                        <form:errors path="lastDate" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Salary</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="salary" class="form-control" ></form:input>
                        <form:errors path="salary" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;"></th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <button class="btn btn-primary" type="submit">Submit</button>
                    </td>
                </tr>
            </form:form>

        </table>
    </div>
</div>