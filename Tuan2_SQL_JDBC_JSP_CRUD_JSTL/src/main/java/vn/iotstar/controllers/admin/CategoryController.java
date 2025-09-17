package vn.iotstar.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import vn.iotstar.models.CategoryModel;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
    maxFileSize = 1024 * 1024 * 10,       // 10MB
    maxRequestSize = 1024 * 1024 * 50     // 50MB
)
@WebServlet(urlPatterns = {"/admin/category"})
public class CategoryController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null) {
            // Hiển thị danh sách
            List<CategoryModel> list = cateService.findAll();
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
        } 
        else if ("add".equals(action)) {
            req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
        }
        else if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            CategoryModel cate = cateService.findById(id);
            req.setAttribute("cate", cate);
            req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
        } 
        else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            cateService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/category");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        // Thư mục lưu ảnh (ngoài Tomcat để không bị xoá khi redeploy)
        String uploadPath = "D:/uploads"; 
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        if ("update".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));

            // Xử lý ảnh
            String fileName = null;
            for (Part part : req.getParts()) {
                if ("images".equals(part.getName()) && part.getSize() > 0) {
                    String originalFile = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    fileName = UUID.randomUUID().toString() + "_" + originalFile;
                    part.write(uploadPath + File.separator + fileName);
                }
            }

            if (fileName == null || fileName.isEmpty()) {
                fileName = req.getParameter("oldImage");  // giữ ảnh cũ
            }

            CategoryModel cate = new CategoryModel();
            cate.setCategoryid(id);
            cate.setCategoryname(name);
            cate.setImages(fileName);
            cate.setStatus(status);

            cateService.update(cate);
            resp.sendRedirect(req.getContextPath() + "/admin/category");
        } 
        else if ("insert".equals(action)) {
            String name = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));

            String fileName = null;
            for (Part part : req.getParts()) {
                if ("images".equals(part.getName()) && part.getSize() > 0) {
                    String originalFile = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    fileName = UUID.randomUUID().toString() + "_" + originalFile;
                    part.write(uploadPath + File.separator + fileName);
                }
            }

            if (fileName == null || fileName.isEmpty()) {
                fileName = "no-image.png"; // ảnh mặc định
            }

            CategoryModel cate = new CategoryModel();
            cate.setCategoryname(name);
            cate.setImages(fileName);
            cate.setStatus(status);

            cateService.insert(cate);
            resp.sendRedirect(req.getContextPath() + "/admin/category");
        }
    }
}
