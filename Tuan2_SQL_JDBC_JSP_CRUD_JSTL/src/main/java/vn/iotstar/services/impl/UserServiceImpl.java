package vn.iotstar.services.impl;

import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.UserService;

import java.sql.Date;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    // Đăng nhập
    @Override
    public UserModel login(String username, String password) {
        UserModel user = userDao.get(username);
        if (user != null && user.getPassWord().equals(password)) {
            return user;
        }
        return null;
    }

    // Lấy user theo username
    @Override
    public UserModel get(String username) {
        return userDao.get(username);
    }

    // Đăng ký tài khoản
    @Override
    public boolean register(String username, String password, String email, String fullname, String phone) {
        // Kiểm tra username/email/phone đã tồn tại chưa
        if (userDao.checkExistUsername(username) || userDao.checkExistEmail(email) || userDao.checkExistPhone(phone)) {
            return false;
        }

        // Tạo ngày hiện tại
        Date date = new Date(System.currentTimeMillis());

        // Tạo UserModel mới bằng setter
        UserModel user = new UserModel();
        user.setEmail(email);
        user.setUserName(username);
        user.setFullName(fullname);
        user.setPassWord(password);
        user.setAvatar(null);       // mặc định null
        user.setRoleid(5);          // role mặc định
        user.setPhone(phone);
        user.setCreatedDate(date);

        // Thêm user vào DB
        userDao.insert(user);
        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }

    @Override
    public void insert(UserModel user) {
        userDao.insert(user);
    }
    
    @Override
    public UserModel getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public boolean updatePassword(UserModel user) {
        return userDao.updatePassword(user);
    }

}
