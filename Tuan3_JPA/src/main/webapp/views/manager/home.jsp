<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Danh sách Category của bạn</h2>
<a href="<c:url value='/manager/category/add'/>">Add category</a>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
        <tr>
            <th>STT</th>
            <th>Images</th>
            <th>CategoryName</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${categories}" var="cate" varStatus="loop">
            <tr>
                <td>${loop.index + 1}</td>
                <td>
                    <img src="<c:url value='/imgUrl?fname=${cate.images}'/>" width="100" height="100"/>
                </td>
                <td>${cate.categoryname}</td>
                <td>
                    <c:choose>
                        <c:when test="${cate.status == 1}">Hoạt động</c:when>
                        <c:otherwise>Khóa</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="<c:url value='/manager/category/edit?id=${cate.categoryId}'/>">Edit</a> |
                    <a href="<c:url value='/manager/category/delete?id=${cate.categoryId}'/>"
                       onclick="return confirm('Are you sure?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
