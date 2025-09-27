package org.example.baitap_26_9_2025.service;

import org.example.baitap_26_9_2025.entity.User;
import org.example.baitap_26_9_2025.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /** CREATE */
    public User create(User u) {
        return userRepository.save(u);
    }

    /** UPDATE */
    public User update(Long id, User input) {
        User exist = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        exist.setFullname(input.getFullname());
        exist.setEmail(input.getEmail());
        exist.setPassword(input.getPassword());
        exist.setPhone(input.getPhone());
        return userRepository.save(exist);
    }

    /** DELETE */
    public boolean delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Xóa liên kết với Category trước khi xóa User
        if (user.getCategories() != null && !user.getCategories().isEmpty()) {
            user.getCategories().clear();
            userRepository.save(user);
        }

        userRepository.delete(user);
        return true;
    }

    /** READ */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
