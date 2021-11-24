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
            <form:form class="form-horizontal" action="${submiturl1}"
                method="post" modelAttribute="classDetails">
                <tr>
                    <th style="width: 40%;">
                        <h4>Add Class Details</h4>
                    </th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%; text-align: right;">
                        <a href="#" onclick="window.location.reload();">Reset <i class="fa fa-refresh"
                                aria-hidden="true"></i></a>
                    </td>
                </tr>
                
                <tr>
                    <th style="width: 40%; text-align: center;">Class(Enter a number) ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="classNo" class="form-control" required="true"></form:input>
                        <form:errors path="classNo" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Section(Enter at max 2 char) ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="section" class="form-control" required="true"></form:input>
                        <form:errors path="section" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;"></th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <button class="btn btn-primary" type="submit" name= "submit" value="1" >${buttonmessage1}</button>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;"></th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <button class="btn btn-primary" type="submit" name= "submit" value="2" >${buttonmessage2}</button>
                    </td>
                </tr>
                
            </form:form>

        </table>
    </div>
    
    <div class="row shadow bg-white rounded" style="border: 1px solid whitesmoke; padding: 0 40px;">
        <table class="table table-borderless mt-4">
            <form:form class="form-horizontal" action="${submiturl2}"
                method="post" modelAttribute="classStudent">
                <tr>
                    <th style="width: 40%;">
                        <h4>View Class</h4>
                    </th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%; text-align: right;">
                        <a href="#" onclick="window.location.reload();">Reset <i class="fa fa-refresh"
                                aria-hidden="true"></i></a>
                    </td>
                </tr>
                
                <tr>
                    <th style="width: 40%; text-align: center;">Class(Enter a number) ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="classDetails.classNo" class="form-control" required="true"></form:input>
                        <form:errors path="classDetails.classNo" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Section(Enter at max 2 char) ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="classDetails.section" class="form-control" required="true"></form:input>
                        <form:errors path="classDetails.section" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Start year("yyyy" form) ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="startYear" class="form-control" required="true"></form:input>
                        <form:errors path="startYear" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;"></th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <button class="btn btn-primary" type="submit">${buttonmessage3}</button>
                    </td>
                </tr>
            </form:form>

        </table>
    </div>
</div>