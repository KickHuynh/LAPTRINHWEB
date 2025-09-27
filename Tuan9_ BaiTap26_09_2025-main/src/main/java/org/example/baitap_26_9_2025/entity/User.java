package org.example.baitap_26_9_2025.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users_gql")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String fullname;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(length = 15)
    private String phone;

    // Many-to-Many với Category
    @ManyToMany
    @JoinTable(
            name = "user_categories", // tên bảng trung gian
            joinColumns = @JoinColumn(name = "user_id"), // khóa ngoại từ User
            inverseJoinColumns = @JoinColumn(name = "category_id") // khóa ngoại từ Category
    )
    private Set<Category> categories = new HashSet<>();
}

