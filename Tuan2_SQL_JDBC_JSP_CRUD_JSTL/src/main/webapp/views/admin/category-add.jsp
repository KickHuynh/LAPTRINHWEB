<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm mới Category</title>
</head>
<body>
    <h2>Thêm mới Category</h2>
    <form action="${pageContext.request.contextPath}/admin/category" 
          method="post" enctype="multipart/form-data">
        <input type="hidden" name="action" value="insert"/>

        <p>Tên: <input type="text" name="categoryname" required /></p>
        <p>Ảnh: <input type="file" name="images" required /></p>
        <p>Trạng thái:
            <select name="status">
                <option value="1">Hiển thị</option>
                <option value="0">Ẩn</option>
            </select>
        </p>

        <button type="submit">Lưu</button>
        <a href="${pageContext.request.contextPath}/admin/category">Hủy</a>
    </form>
</body>
</html>
