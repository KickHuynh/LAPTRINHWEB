package vn.iotstar.controllers;

import vn.iotstar.models.UserModel;
import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;
import vn.iotstar.utils.SendMail;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;

@WebServlet("/forgot")
public class ForgotController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 8;

    private String randomPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/forgot.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        UserService service = new UserServiceImpl();
        UserModel user = service.get(email); // cần thêm getByEmail trong service/DAO

        String alertMsg = "";

        if (user == null) {
            alertMsg = "Email không tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/forgot.jsp").forward(req, resp);
            return;
        }

        // Tạo mật khẩu mới random
        String newPassword = randomPassword();
        user.setPassWord(newPassword);

        // Cập nhật mật khẩu vào database
        service.insert(user); // hoặc tạo hàm updatePassword trong DAO/Service

        // Gửi mail
        SendMail sm = new SendMail();
        String subject = "Mật khẩu mới của bạn";
        String content = "Mật khẩu mới của bạn là: " + newPassword;
        sm.sendMail(user.getEmail(), "Quên mật khẩu", content);

        alertMsg = "Mật khẩu mới đã được gửi tới email của bạn!";
        req.setAttribute("alert", alertMsg);
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }
}
