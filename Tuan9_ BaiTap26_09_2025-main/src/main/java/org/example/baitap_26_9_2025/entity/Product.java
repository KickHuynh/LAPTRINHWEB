package org.example.baitap_26_9_2025.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products_gql")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    private int quantity;

    @Column(length = 500)
    private String description;

    private Double price;

    // Many-to-One với User
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false) // foreign key
    private Category category;
}

