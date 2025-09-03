<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa Category</title>
</head>
<body>
    <h2>Sửa Category</h2>
    <form action="${pageContext.request.contextPath}/admin/category" method="post">
        <input type="hidden" name="action" value="update"/>
        <input type="hidden" name="id" value="${cate.categoryid}"/>

        <p>Tên: <input type="text" name="categoryname" value="${cate.categoryname}"/></p>
        <p>Ảnh: <input type="text" name="images" value="${cate.images}"/></p>
        <p>Trạng thái:
            <select name="status">
                <option value="1" <c:if test="${cate.status == 1}">selected</c:if>>Hiển thị</option>
                <option value="0" <c:if test="${cate.status == 0}">selected</c:if>>Ẩn</option>
            </select>
        </p>

        <button type="submit">Cập nhật</button>
    </form>
</body>
</html>
