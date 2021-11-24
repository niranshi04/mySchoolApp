<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>
<%! int fontSize; %>
<div class="container-fluid custom-container">
    <div class="table-responsive mt-2">
        <table class="table table-hover mt-4 table-sort">
        
            <thead>
                <tr>
                    <th>Class</th>
                    <th>Jan</th>
                    <th>Feb</th>
                    <th>Mar</th>
                    <th>Apr</th>
                    <th>May</th>
                    <th>Jun</th>
                    <th>Jul</th>
                    <th>Aug</th>
                    <th>Sep</th>
                    <th>Oct</th>
                    <th>Nov</th>
                    <th>Dec</th>
                </tr>
            </thead>
            <tbody>
           <c:forEach items="${feeDetails}" var="feeDetail" varStatus="loop">
                <tr>
                    <td>${loop.index+1}</td>
                    <td>${feeDetail.get((1).intValue())}</td>
                    <td>${feeDetail.get((2).intValue())}</td>
                    <td>${feeDetail.get((3).intValue())}</td>
                    <td>${feeDetail.get((4).intValue())}</td>
                    <td>${feeDetail.get((5).intValue())}</td>
                    <td>${feeDetail.get((6).intValue())}</td>
                    <td>${feeDetail.get((7).intValue())}</td>
                    <td>${feeDetail.get((8).intValue())}</td>
                    <td>${feeDetail.get((9).intValue())}</td>
                    <td>${feeDetail.get((10).intValue())}</td>
                    <td>${feeDetail.get((11).intValue())}</td>
                    <td>${feeDetail.get((12).intValue())}</td>
               
      
                </tr>
          </c:forEach>
            </tbody>
        </table>
    </div>
</div>

