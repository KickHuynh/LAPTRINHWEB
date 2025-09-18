<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="bg-light">
    <div class="container py-5" style="max-width: 460px;">
        <h3 class="mb-4 text-center">Đăng nhập</h3>

        <c:if test="${alert != null}">
            <div class="alert alert-danger">${alert}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="mb-3">
                <label class="form-label">Tài khoản</label>
                <input type="text" name="username" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Mật khẩu</label>
                <input type="password" name="password" class="form-control" required>
            </div>
            <div class="form-check mb-3">
                <input class="form-check-input" type="checkbox" name="remember" id="remember">
                <label class="form-check-label" for="remember">Ghi nhớ tôi</label>
            </div>
            <button class="btn btn-primary w-100" type="submit">Đăng nhập</button>
        </form>

        <div class="text-center mt-3">
            <a href="${pageContext.request.contextPath}/register">Tạo tài khoản mới</a>
        </div>
        <div class="text-center mt-3">
            <a href="${pageContext.request.contextPath}/forgot">Quên mật khẩu</a>
        </div>
    </div>
</body>
</html>
