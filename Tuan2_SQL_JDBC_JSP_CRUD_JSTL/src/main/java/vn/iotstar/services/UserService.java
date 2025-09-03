package vn.iotstar.services;

import vn.iotstar.models.UserModel;

public interface UserService {
	 // Đăng nhập
    UserModel login(String username, String password);

    // Lấy thông tin user theo username
    UserModel get(String username);
    
    // Thêm user mới vào cơ sở dữ liệu
    void insert(UserModel user);

  

    // Kiểm tra email đã tồn tại chưa
    boolean checkExistEmail(String email);

    // Kiểm tra username đã tồn tại chưa
    boolean checkExistUsername(String username);

    // Kiểm tra số điện thoại đã tồn tại chưa
    boolean checkExistPhone(String phone);
    
    boolean register(String username, String password, String email, String fullname, String phone);

    UserModel getByEmail(String email);
    boolean updatePassword(UserModel user);


}
