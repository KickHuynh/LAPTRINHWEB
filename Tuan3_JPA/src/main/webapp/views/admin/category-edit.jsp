<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Edit Category</h2>
<form action="<c:url value='/admin/category/update'/>" method="post" enctype="multipart/form-data">
    <input type="hidden" name="categoryId" value="${cate.categoryId}">

    <label>Category name:</label><br>
    <input type="text" name="categoryname" value="${cate.categoryname}" required><br>

    <label>Images:</label><br>
    <img height="150" width="200" src="<c:url value='/imgUrl?fname=${cate.images}'/>" /><br>
    <input type="file" onchange="chooseFile(this)" name="images"><br>

    <p>Status:</p>
    <input type="radio" name="status" value="1" ${cate.status == 1 ? 'checked' : ''}> Đang hoạt động<br>
    <input type="radio" name="status" value="0" ${cate.status == 0 ? 'checked' : ''}> Khóa<br><br>

    <input type="submit" value="Update">
</form>
