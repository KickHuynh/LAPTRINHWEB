package vn.iotstar.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"//", "/home", "/trangchu"})
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
 
        resp.setContentType("text/html;charset=UTF-8");

        String name = req.getParameter("ten");
 
        // Lấy writer để ghi ra response
        PrintWriter out = resp.getWriter();

        try {
            out.println("<html>");
            out.println("<head><title>HomeController</title></head>");
            out.println("<body>");
            out.println("<h1>Hello World từ HomeController! </h1>"+name);
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
}
