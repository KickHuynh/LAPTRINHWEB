package vn.iotstar.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

        // Lấy dữ liệu từ form
        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        if ("tai".equals(user) && "123".equals(pass)) {
            // Tạo cookie
            Cookie cookie = new Cookie("username", user);
            cookie.setMaxAge(30); // tồn tại 30s
            resp.addCookie(cookie);

            // chuyển sang HelloServlet
            resp.sendRedirect(req.getContextPath() + "/hello");
        } else {
            // quay lại login
            resp.sendRedirect(req.getContextPath() + "/Login.html");
        }
    }
}
