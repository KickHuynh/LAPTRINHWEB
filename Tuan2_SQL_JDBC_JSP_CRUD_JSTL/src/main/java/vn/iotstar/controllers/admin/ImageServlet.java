package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/load-image")
public class ImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Thay đường dẫn này bằng thư mục chứa ảnh của bạn
    private static final String IMAGE_DIR = "D:/images/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fileName = request.getParameter("fname");
        if (fileName == null || fileName.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu tham số fname");
            return;
        }

        File imageFile = new File(IMAGE_DIR + fileName);
        if (!imageFile.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy ảnh");
            return;
        }

        // Xác định kiểu content (chỉ demo jpg/png, có thể mở rộng thêm)
        if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
            response.setContentType("image/jpeg");
        } else if (fileName.toLowerCase().endsWith(".png")) {
            response.setContentType("image/png");
        } else {
            response.setContentType("application/octet-stream");
        }

        // Gửi file ra output
        try (FileInputStream fis = new FileInputStream(imageFile);
             OutputStream os = response.getOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
    }
}
