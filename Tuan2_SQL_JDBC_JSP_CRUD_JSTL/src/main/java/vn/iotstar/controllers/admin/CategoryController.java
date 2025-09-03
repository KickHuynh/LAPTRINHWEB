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

@WebServlet(urlPatterns = {"/admin/Category"})
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public CategoryService cateService = new CategoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<CategoryModel> list = cateService.findAll();
		req.setAttribute("listcase", list);
		req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req,resp);
		
	}

	

}
