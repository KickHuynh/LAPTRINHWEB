package vn.iotstar.dao.impl;

import vn.iotstar.configs.DBConnection;
import vn.iotstar.dao.UserDao;
import vn.iotstar.models.UserModel;

import java.sql.*;
import java.util.List;

public class UserDaoImpl implements UserDao {
    
    // Lấy user theo username
    @Override
    public UserModel get(String username) {
        String sql = "SELECT * FROM [User] WHERE username = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UserModel user = new UserModel();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setUserName(rs.getString("username"));
                    user.setFullName(rs.getString("fullname"));
                    user.setPassWord(rs.getString("password"));
                    user.setAvatar(rs.getString("avatar"));
                    user.setRoleid(rs.getInt("roleid"));
                    user.setPhone(rs.getString("phone"));
                    user.setCreatedDate(rs.getDate("createdDate"));
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm user mới
    @Override
    public void insert(UserModel user) {
        String sql = "INSERT INTO [User] (email, username, fullname, password, avatar, roleid, phone, createdDate) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getPassWord());
            ps.setString(5, user.getAvatar());
            ps.setInt(6, user.getRoleid());
            ps.setString(7, user.getPhone());
            ps.setDate(8, user.getCreatedDate()); // nếu cột DB là DATE, dùng setDate
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Kiểm tra email đã tồn tại
    @Override
    public boolean checkExistEmail(String email) {
        boolean duplicate = false;
        String query = "SELECT * FROM [User] WHERE email = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    duplicate = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return duplicate;
    }

    // Kiểm tra username đã tồn tại
    @Override
    public boolean checkExistUsername(String username) {
        boolean duplicate = false;
        String query = "SELECT * FROM [User] WHERE username = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    duplicate = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return duplicate;
    }

    // Kiểm tra phone đã tồn tại
    @Override
    public boolean checkExistPhone(String phone) {
        boolean duplicate = false;
        String query = "SELECT * FROM [User] WHERE phone = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    duplicate = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return duplicate;
    }

    // Các phương thức khác chưa dùng
    @Override
    public List<UserModel> findAll() {
        return null;
    }

    @Override
    public UserModel findById(int id) {
        return null;
    }
    
    @Override
    public UserModel getByEmail(String email) {
        String sql = "SELECT * FROM [User] WHERE email = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UserModel user = new UserModel();
                    user.setId(rs.getInt("id"));
                    user.setUserName(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setFullName(rs.getString("fullname"));
                    user.setPassWord(rs.getString("password"));
                    user.setRoleid(rs.getInt("roleid"));
                    user.setPhone(rs.getString("phone"));
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updatePassword(UserModel user) {
        String sql = "UPDATE [User] SET password = ? WHERE email = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getPassWord());
            ps.setString(2, user.getEmail());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
