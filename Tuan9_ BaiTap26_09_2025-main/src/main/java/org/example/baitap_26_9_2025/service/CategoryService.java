package org.example.baitap_26_9_2025.service;

import org.example.baitap_26_9_2025.entity.Category;
import org.example.baitap_26_9_2025.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /** CREATE */
    public Category create(Category c) {
        return categoryRepository.save(c);
    }

    /** UPDATE */
    public Category update(Long id, Category input) {
        Category ex = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        ex.setName(input.getName());
        ex.setImages(input.getImages());
        return categoryRepository.save(ex);
    }

    /** DELETE */
    public boolean delete(Long id) {
        Category ex = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Nếu Category vẫn có Product, không cho xóa
        if (ex.getProducts() != null && !ex.getProducts().isEmpty()) {
            throw new RuntimeException("Cannot delete Category because it still has Products.");
        }

//        // Nếu Category vẫn liên kết với User (many-to-many)
//        if (ex.getUsers() != null && !ex.getUsers().isEmpty()) {
//            throw new RuntimeException("Cannot delete Category because it is assigned to Users.");
//        }

        categoryRepository.delete(ex);
        return true;
    }

    /** READ */
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}

