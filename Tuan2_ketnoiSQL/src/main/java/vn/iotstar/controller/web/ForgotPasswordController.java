package vn.iotstar.controller.web;

import vn.iotstar.service.UserService;
import vn.iotstar.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/forgot")
public class ForgotPasswordController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Hiển thị form quên mật khẩu
        req.getRequestDispatcher("/views/forgot.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");

        UserService service = new UserServiceImpl();
        boolean success = service.resetPassword(username, email); // Hàm tự viết trong UserServiceImpl

        if (success) {
            req.setAttribute("message", "Mật khẩu mới đã được gửi đến email của bạn.");
        } else {
            req.setAttribute("message", "Thông tin không chính xác, vui lòng kiểm tra lại.");
        }

        req.getRequestDispatcher("/views/forgot.jsp").forward(req, resp);
    }
}
