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
                method="post" modelAttribute="student">
                <tr>
                    <th style="width: 40%;">
                        <h4>Step 1: Student Details</h4>
                    </th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%; text-align: right;">
                        <a href="#" onclick="window.location.reload();">Reset <i class="fa fa-refresh"
                                aria-hidden="true"></i></a>
                    </td>
                </tr>
                
                <tr>
                    <th style="width: 40%; text-align: center;">Name ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="name" class="form-control" required="true"></form:input>
                        <form:errors path="name" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Email Address ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="email" path="user.emailAddress" class="form-control" required="true"></form:input>
                        <form:errors path="user.emailAddress" style="color: red;"></form:errors>
                    </td>
                </tr>
                
                
                
                <tr>
                    <th style="width: 40%; text-align: center;">Gender ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:select class="form-control" path="gender" required="true">
                            <form:option value="Male">Male</form:option>
                            <form:option value="Female">Female</form:option>
                            <form:option value="Not Specified">Not Specified</form:option>
                        </form:select>
                        <form:errors path="gender" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Date of Birth ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="date" path="dateOfBirth" class="form-control" required="true" max="2099-01-01" min="1995-01-01"></form:input>
                        <form:errors path="dateOfBirth" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Address ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="houseNumber" class="form-control" placeholder="House Number"></form:input>
                        <form:errors path="houseNumber" style="color: red;"></form:errors>
                        <form:input type="text" path="street" class="form-control" required="true" placeholder="Street"></form:input>
                        <form:errors path="street" style="color: red;"></form:errors>
                        <form:input type="text" path="city" class="form-control" required="true" placeholder="City"></form:input>
                        <form:errors path="city" style="color: red;"></form:errors>
                        <form:input type="text" path="state" class="form-control" required="true" placeholder="State"></form:input>
                        <form:errors path="state" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Date of Admission ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="date" path="dateOfAdmission" class="form-control" required="true" max="2099-01-01" min="1995-01-01"></form:input>
                        <form:errors path="dateOfAdmission" style="color: red;"></form:errors>
                    </td>
                </tr>
                
                <tr>
                    <th style="width: 40%; text-align: center;">Date of Leaving School</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="date" path="dateOfLeavingSchool" class="form-control" max="2099-01-01" min="1995-01-01"></form:input>
                        <form:errors path="dateOfLeavingSchool" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Phone Number ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="phoneNumber" class="form-control" required="true"></form:input>
                        <form:errors path="phoneNumber" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;"></th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <button class="btn btn-primary" type="submit">${buttonmessage}</button>
                    </td>
                </tr>
            </form:form>

        </table>
    </div>
</div>