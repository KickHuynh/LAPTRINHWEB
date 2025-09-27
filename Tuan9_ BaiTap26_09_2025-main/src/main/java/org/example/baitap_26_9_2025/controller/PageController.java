package org.example.baitap_26_9_2025.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class PageController {

    // Trang chính hiển thị danh sách User
    @GetMapping("/")
    public String home() {
        return "user"; // templates/user.html
    }

    // Trang Category - truyền userId để biết User đã chọn
    @GetMapping("/categories")
    public String categoryPage(@RequestParam("userId") Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "category";
    }

    @GetMapping("/products")
    public String productPage(@RequestParam(value = "categoryId", required = false) Long categoryId,
                              Model model) {
        System.out.println("CategoryId: " + categoryId);
        model.addAttribute("categoryId", categoryId);
        return "product";
    }
//    @GetMapping("/products")
//    public String productPage(@RequestParam(value = "categoryId", required = false) Long categoryId,
//                              Model model) {
//        if (categoryId != null) {
//            return "redirect:/products?categoryId=" + categoryId;
//        }
//        return "product";
//    }
}
