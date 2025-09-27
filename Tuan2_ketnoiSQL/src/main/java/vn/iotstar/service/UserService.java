package vn.iotstar.service;

import vn.iotstar.model.User;

public interface UserService {

    // Đăng nhập
    User login(String username, String password);

    // Lấy thông tin user theo username
    User get(String username);

    // Đăng ký
    boolean register(String username, String password, String email, String fullname, String phone);

    // Kiểm tra tồn tại
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);

    // Cập nhật mật khẩu (reset password)
    boolean resetPassword(String username, String email);

    // Thêm user trực tiếp (nếu cần)
    void insert(User user);
}
