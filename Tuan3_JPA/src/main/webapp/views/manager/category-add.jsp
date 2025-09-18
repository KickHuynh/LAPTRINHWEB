<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Add Category</h2>
<form action="<c:url value='/manager/category/insert'/>" method="post" enctype="multipart/form-data">
    <label>Category name:</label><br>
    <input type="text" name="categoryname" required><br>

    <label>Images:</label><br>
    <input type="file" onchange="chooseFile(this)" name="images"><br>

    <p>Status:</p>
    <input type="radio" name="status" value="1" checked> Đang hoạt động<br>
    <input type="radio" name="status" value="0"> Khóa<br><br>

    <input type="submit" value="Insert">
</form>
