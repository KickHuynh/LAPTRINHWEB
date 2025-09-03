<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5" style="max-width: 500px;">
    <form action="${pageContext.request.contextPath}/register" method="post">
        <h2 class="mb-3">Tạo tài khoản mới</h2>

        <c:if test="${alert != null}">
            <div class="alert alert-danger">${alert}</div>
        </c:if>

        <div class="mb-3">
            <label>Tài khoản</label>
            <input type="text" name="username" class="form-control" required>
        </div>

        <div class="mb-3">
            <label>Mật khẩu</label>
            <input type="password" name="password" class="form-control" required>
        </div>

        <div class="mb-3">
            <label>Email</label>
            <input type="email" name="email" class="form-control" required>
        </div>

        <div class="mb-3">
            <label>Họ tên</label>
            <input type="text" name="fullname" class="form-control" required>
        </div>

        <div class="mb-3">
            <label>Số điện thoại</label>
            <input type="text" name="phone" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-primary w-100">Đăng ký</button>
    </form>
</div>
</body>
</html>
