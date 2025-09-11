package vn.iotstar;

import vn.iotstar.dao.CategoryDAO;
import vn.iotstar.entity.Category;

public class MainTest {
    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();

        // Thêm mới
        Category c1 = new Category();
        c1.setName("Trà sữa");
        dao.insert(c1);

        // Sửa
        c1.setName("Trà đào");
        dao.update(c1);

        // Tìm
        Category c2 = dao.findById(c1.getId());
        System.out.println("Tìm thấy: " + c2.getName());

        // Danh sách
        dao.findAll().forEach(c -> System.out.println(c.getId() + " - " + c.getName()));

        // Xóa
        dao.delete(c1.getId());
    }
}
