package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Xóa session
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Xóa cookie "remember me"
        Cookie cookie = new Cookie("remember", "");
        cookie.setMaxAge(0); // Xóa cookie
        cookie.setPath(req.getContextPath());
        resp.addCookie(cookie);

        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
