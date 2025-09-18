package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.ultis.Constant;

@WebServlet("/imgUrl")
public class ImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String fileName = req.getParameter("fname");
        if (fileName == null || fileName.equals("")) {
            return;
        }

        File file = new File(Constant.UPLOAD_DIRECTORY + File.separator + fileName);
        if (!file.exists()) {
            return;
        }

        resp.setContentType(getServletContext().getMimeType(file.getName()));
        resp.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");

        java.nio.file.Files.copy(file.toPath(), resp.getOutputStream());
    }
}

