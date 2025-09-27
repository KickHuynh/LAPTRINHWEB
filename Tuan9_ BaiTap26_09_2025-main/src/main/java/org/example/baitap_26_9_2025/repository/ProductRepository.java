package org.example.baitap_26_9_2025.repository;

import org.example.baitap_26_9_2025.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByPriceAsc();

    // Lấy các product của 1 category: vì relation Category <-> User, ta join qua user.categories
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    List<Product> findProductsByCategoryId(@Param("id") Long categoryId);
    @Query("SELECT p FROM Product p WHERE p.category.id = :id")
    List<Product> findByCategoryId(@Param("id") Long id);

}
