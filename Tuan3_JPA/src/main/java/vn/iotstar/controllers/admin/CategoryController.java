package vn.iotstar.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import vn.iotstar.entity.Category;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;
import vn.iotstar.ultis.Constant;

@WebServlet(urlPatterns = {
        "/admin/categories",
        "/admin/category/list",
        "/admin/category/edit",
        "/admin/category/update",
        "/admin/category/delete",
        "/admin/category/add",
        "/admin/category/insert"
})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ICategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String url = req.getRequestURI();

        if (url.contains("/admin/category/edit")) {
            // Lấy id trên URL
            int id = Integer.parseInt(req.getParameter("id"));
            // Lấy category từ DB
            Category cate = cateService.findById(id);
            // Truyền cho JSP
            req.setAttribute("cate", cate);
            req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);

        } else if (url.contains("/admin/category/add")) {
            req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);

        } else if (url.contains("/admin/category/delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                cateService.delete(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/admin/categories");

        } else {
            // Mặc định: hiển thị danh sách
            List<Category> categories = cateService.findAll();
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String url = req.getRequestURI();

        if (url.contains("/admin/category/update")) {
            // UPDATE
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            String categoryName = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));

            Category category = cateService.findById(categoryId);

            if (category != null) {
                category.setCategoryname(categoryName);
                category.setStatus(status);

                // Xử lý ảnh
                String oldFile = category.getImages();
                String fName = "";
                String uploadPath = Constant.UPLOAD_DIRECTORY;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();

                try {
                    Part part = req.getPart("images");
                    if (part != null && part.getSize() > 0) {
                        String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                        int index = fileName.lastIndexOf(".");
                        String ext = fileName.substring(index + 1);
                        fName = System.currentTimeMillis() + "." + ext;
                        part.write(uploadPath + "/" + fName);
                        category.setImages(fName);
                    } else {
                        category.setImages(oldFile); // giữ nguyên ảnh cũ
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    category.setImages(oldFile);
                }

                cateService.update(category);
            }

            resp.sendRedirect(req.getContextPath() + "/admin/categories");

        } else if (url.contains("/admin/category/insert")) {
            // INSERT
            String nameParam = req.getParameter("categoryname");
            String statusParam = req.getParameter("status");

            int status = (statusParam != null && !statusParam.isEmpty())
                    ? Integer.parseInt(statusParam) : 0;

            Category category = new Category();
            category.setCategoryname(nameParam);
            category.setStatus(status);

            // Xử lý file ảnh
            String fName = "";
            String uploadPath = Constant.UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            try {
                Part part = req.getPart("images");
                if (part != null && part.getSize() > 0) {
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = fileName.lastIndexOf(".");
                    String ext = fileName.substring(index + 1);
                    fName = System.currentTimeMillis() + "." + ext;
                    part.write(uploadPath + "/" + fName);
                    category.setImages(fName);
                } else {
                    category.setImages("avata.jpg");
                }
            } catch (Exception e) {
                e.printStackTrace();
                category.setImages("avata.jpg");
            }

            cateService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }
}
