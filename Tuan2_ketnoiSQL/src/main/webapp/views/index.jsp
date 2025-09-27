<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vn.iotstar.model.User" %>
<%
    // Lấy user từ session
    User account = (User) session.getAttribute("account");
    if (account == null) {
        // Nếu chưa đăng nhập thì chuyển sang login.jsp
        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ - Kickhuynh</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            text-align: center;
            padding-top: 100px;
        }
        h1 {
            color: #2e8b57;
        }
        p {
            font-size: 20px;
            color: #555;
        }
        .welcome-box {
            background-color: #ffffff;
            border-radius: 10px;
            padding: 50px;
            width: 60%;
            margin: auto;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        a {
            text-decoration: none;
            color: #2e8b57;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="welcome-box">
        <p>Chào mừng bạn đến với trang web của Kickhuynh!</p>
        <p><a href="<%= request.getContextPath() %>/logout">Đăng xuất</a></p>
    </div>
</body>
</html>
