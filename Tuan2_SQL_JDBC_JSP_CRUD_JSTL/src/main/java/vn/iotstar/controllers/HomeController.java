package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/admin/home", "/manager/home", "/home"})
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String uri = req.getRequestURI();

        if (uri.endsWith("/admin/home")) {
            req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
        } else if (uri.endsWith("/manager/home")) {
            req.getRequestDispatcher("/views/manager/home.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
        }
    }
}
