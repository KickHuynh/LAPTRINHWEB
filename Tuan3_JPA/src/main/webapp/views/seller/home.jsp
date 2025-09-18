<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Tất cả Category</h2>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
        <tr>
            <th>STT</th>
            <th>Images</th>
            <th>CategoryName</th>
            <th>Status</th>
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
            </tr>
        </c:forEach>
    </tbody>
</table>
