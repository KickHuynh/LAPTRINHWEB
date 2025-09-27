package org.example.baitap_26_9_2025.service;

import org.example.baitap_26_9_2025.entity.Category;
import org.example.baitap_26_9_2025.entity.Product;
import org.example.baitap_26_9_2025.repository.CategoryRepository;
import org.example.baitap_26_9_2025.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /** Lấy danh sách sản phẩm sắp xếp theo giá tăng dần */
    public List<Product> getAllSortedByPriceAsc() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    /** Lấy danh sách sản phẩm theo category */
    public List<Product> getByCategory(Long categoryId) {
//        return productRepository.findProductsByCategoryId(categoryId);
        return productRepository.findByCategoryId(categoryId);
    }

    /** Tạo sản phẩm mới, gắn với 1 category */
    public Product createProduct(String title, int quantity, String description, Double price, Long categoryId) {
        Category c = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product p = Product.builder()
                .title(title)
                .quantity(quantity)
                .description(description)
                .price(price)
                .category(c)
                .build();
        return productRepository.save(p);
    }

    /** Cập nhật thông tin sản phẩm */
    public Product updateProduct(Long id, String title, int quantity, String description, Double price) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        p.setTitle(title);
        p.setQuantity(quantity);
        p.setDescription(description);
        p.setPrice(price);
        return productRepository.save(p);
    }

    /** Xóa sản phẩm */
    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }

    /** Lấy tất cả sản phẩm */
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /** Lấy sản phẩm theo ID */
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
