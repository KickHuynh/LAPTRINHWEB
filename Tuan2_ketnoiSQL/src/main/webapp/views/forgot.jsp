<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu</title>
<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body class="bg-light">
    <div class="container py-5" style="max-width: 460px;">
        <h3 class="mb-4 text-center">Quên mật khẩu</h3>

        <!-- Hiển thị thông báo -->
        <c:if test="${message != null}">
            <div class="alert alert-info">${message}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/forgot" method="post">
            <div class="mb-3">
                <label class="form-label">Tài khoản</label>
                <input type="text" name="username" class="form-control" placeholder="Nhập username" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" name="email" class="form-control" placeholder="Nhập email đã đăng ký" required>
            </div>
            <button class="btn btn-primary w-100" type="submit">Gửi yêu cầu</button>
        </form>

        <div class="text-center mt-3">
            <a href="${pageContext.request.contextPath}/login">Quay lại đăng nhập</a>
        </div>
    </div>
</body>
</html>
