package vn.iotstar.controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.models.CategoryModel;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = {"/admin/category"})
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            // Hiển thị danh sách
            List<CategoryModel> list = cateService.findAll();
            req.setAttribute("listcase", list);
            req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
        } 
        else if (action.equals("edit")) {
            // Lấy dữ liệu để sửa
            int id = Integer.parseInt(req.getParameter("id"));
            CategoryModel cate = cateService.findById(id);
            req.setAttribute("cate", cate);
            req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
        } 
        else if (action.equals("delete")) {
            //  Xóa
            int id = Integer.parseInt(req.getParameter("id"));
            cateService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/category");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("update".equals(action)) {
            // 👉 Cập nhật
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("categoryname");
            String images = req.getParameter("images");
            int status = Integer.parseInt(req.getParameter("status"));

            CategoryModel cate = new CategoryModel();
            cate.setCategoryid(id);
            cate.setCategoryname(name);
            cate.setImages(images);
            cate.setStatus(status);

            cateService.update(cate);

            resp.sendRedirect(req.getContextPath() + "/admin/category");
        }
    }
}
