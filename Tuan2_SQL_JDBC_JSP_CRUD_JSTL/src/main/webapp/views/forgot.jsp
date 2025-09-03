<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quên mật khẩu</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5" style="max-width: 400px;">
    <form action="${pageContext.request.contextPath}/forgot" method="post">
        <h3 class="mb-3">Quên mật khẩu</h3>

        <c:if test="${alert != null}">
            <div class="alert alert-danger">${alert}</div>
        </c:if>

        <div class="mb-3">
            <label>Email đăng ký</label>
            <input type="email" name="email" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-primary w-100">Gửi mật khẩu mới</button>
    </form>
</div>
</body>
</html>
