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
                            REGISTER
                        </div>
                        <div class="card-body">
                            <form:form class="form-horizontal" action="/user/register" method="post" modelAttribute="user">
                                <div class="form-group row">
                                    <label class="col-3 control-label">Email Address: ${mandatory}</label>
                                    <div class="col-9">
                                        <form:input type="email" path="emailAddress" class="form-control" placeholder="Enter the email address" required="true"></form:input>
                                        <div class="ml-2" style="text-align: left;">
                                            <form:errors path="emailAddress" style="color: red;"></form:errors>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-3 control-label">Registering as: ${mandatory}</label>
                                    <div class="col-9">
                                        <form:select class="form-control" path="role" required="true">
                                            <form:option value="ROLE_STUDENT">Student</form:option>
                                            <form:option value="ROLE_TEACHER">Teacher</form:option>
                                        </form:select>
                                        <form:errors path="role" style="color: red;"></form:errors>
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
