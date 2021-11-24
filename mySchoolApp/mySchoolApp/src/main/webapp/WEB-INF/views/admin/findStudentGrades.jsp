<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="login-form section text-center py-5">
    <div class="container">
        <div class="row">
            <div class="col-sm-8 offset-sm-2">
                <div class="mt-5">
                    <div class="card card-info">
                        <div class="card-header" style="color: #fff; background-color:#333;">
                            Find Student Grades
                        </div>
                        <div class="card-body">
                            <form:form class="form-horizontal" action="/admin/findStudentGrades" method="post" modelAttribute="grades">
                                <div class="form-group row">
                                    <label class="col-3 control-label">RegisrationNo: ${mandatory}</label>
                                    <div class="col-9">
                                        <form:input type="text" path="classStudent.student.registrationNo" class="form-control" placeholder="Enter the registration No." required="true"></form:input>
                                        <div class="ml-2" style="text-align: left;">
                                            <form:errors path="classStudent.student.registrationNo" style="color: red;"></form:errors>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-3 control-label">Class No: ${mandatory}</label>
                                    <div class="col-9">
                                        <form:input type="text" path="classStudent.classDetails.classNo" class="form-control" placeholder="Enter the class No." required="true"></form:input>
                                        <div class="ml-2" style="text-align: left;">
                                            <form:errors path="classStudent.classDetails.classNo" style="color: red;"></form:errors>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-3 control-label">Section(Enter at max 2 char) ${mandatory}</label>
                                    <div class="col-9">
                                        <form:input type="text" path="classStudent.classDetails.section" class="form-control" placeholder="Enter the section." required="true"></form:input>
                                        <div class="ml-2" style="text-align: left;">
                                            <form:errors path="classStudent.classDetails.section" style="color: red;"></form:errors>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-3 control-label">Start year("yyyy" form) ${mandatory}</label>
                                    <div class="col-9">
                                        <form:input type="text" path="classStudent.startYear" class="form-control" placeholder="Enter the Start Year" required="true"></form:input>
                                        <div class="ml-2" style="text-align: left;">
                                            <form:errors path="classStudent.startYear" style="color: red;"></form:errors>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-3 control-label">SubjectId ${mandatory}</label>
                                    <div class="col-9">
                                        <form:input type="text" path="subject.subjectId" class="form-control" placeholder="Enter the SubjectId" required="true"></form:input>
                                        <div class="ml-2" style="text-align: left;">
                                            <form:errors path="subject.subjectId" style="color: red;"></form:errors>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group mt-2">
                                        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
