package vn.iotstar.service.impl;

import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.model.User;
import vn.iotstar.service.UserService;

import java.util.Random;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public boolean register(String username, String password, String email, String fullname, String phone) {
        if (userDao.checkExistUsername(username)) {
            return false;
        }
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        userDao.insert(new User(email, username, fullname, password, null, 5, phone, date));
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
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public User login(String username, String password) {
        return ((UserDaoImpl) userDao).getUserByUsernameAndPassword(username, password);
    }

    @Override
    public User get(String username) {
        return ((UserDaoImpl) userDao).getUserByUsername(username);
    }

    @Override
    public boolean resetPassword(String username, String email) {
        User user = ((UserDaoImpl) userDao).getUserByUsername(username);
        if (user == null || !user.getEmail().equalsIgnoreCase(email)) {
            return false;
        }

        String tempPassword = generateTempPassword(8);
        boolean updated = ((UserDaoImpl) userDao).updatePassword(username, tempPassword);

        if (updated) {
            // Gửi email cho user (hiện demo bằng console)
            System.out.println("Gửi email tới " + email + " với mật khẩu tạm thời: " + tempPassword);
            return true;
        }
        return false;
    }

    private String generateTempPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
