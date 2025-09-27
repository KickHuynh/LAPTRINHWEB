package com.example.demo.Models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel {
    private Integer categoryId;
    private String name;
    private boolean edit;
}
