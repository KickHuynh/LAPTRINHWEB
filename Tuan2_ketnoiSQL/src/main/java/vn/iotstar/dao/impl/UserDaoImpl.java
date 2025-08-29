package vn.iotstar.dao.impl;

import vn.iotstar.configs.DBConnection;
import vn.iotstar.dao.UserDao;
import vn.iotstar.model.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public User getUserByUsernameAndPassword(String username, String password) {
		String sql = "SELECT * FROM [User] WHERE username = ? AND password = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username.trim()); // tránh khoảng trắng thừa
			ps.setString(2, password.trim());

			rs = ps.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id")); // nếu có cột id
				user.setUserName(rs.getString("username"));
				user.setPassWord(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setFullName(rs.getString("fullname"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleid(rs.getInt("roleid"));
				user.setPhone(rs.getString("phone"));
				user.setCreatedDate(rs.getDate("createdDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null; // nếu không tìm thấy user
	}

	@Override
	public void insert(User user) {
		// Chỉnh lại thứ tự cột cho khớp với database
		String sql = "INSERT INTO [User](username, password, email, fullname, avatar, roleid, phone, createdDate) "
				+ "VALUES (?,?,?,?,?,?,?,?)";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, user.getUserName()); // username
			ps.setString(2, user.getPassWord()); // password
			ps.setString(3, user.getEmail()); // email
			ps.setString(4, user.getFullName()); // fullname
			ps.setString(5, user.getAvatar()); // avatar
			ps.setInt(6, user.getRoleid()); // roleid
			ps.setString(7, user.getPhone()); // phone

			// Nếu createdDate trong model là java.util.Date
			ps.setTimestamp(8, new java.sql.Timestamp(user.getCreatedDate().getTime()));

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	@Override
	public boolean checkExistEmail(String email) {
		String query = "SELECT 1 FROM [User] WHERE email = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeAll();
		}
	}

	@Override
	public boolean checkExistUsername(String username) {
		String query = "SELECT 1 FROM [User] WHERE username = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeAll();
		}
	}

	@Override
	public boolean checkExistPhone(String phone) {
		String query = "SELECT 1 FROM [User] WHERE phone = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, phone);
			rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeAll();
		}
	}

	// Hàm đóng connection, statement, resultset
	private void closeAll() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		UserDaoImpl dao = new UserDaoImpl();

		String testUsername = "admin"; // nhập username test
		String testPassword = "123"; // nhập password test

		User user = dao.getUserByUsernameAndPassword(testUsername, testPassword);

		if (user != null) {
			System.out.println("Login thành công!");
			System.out.println("ID: " + user.getId());
			System.out.println("Username: " + user.getUserName());
			System.out.println("Fullname: " + user.getFullName());
			System.out.println("Email: " + user.getEmail());
			System.out.println("Phone: " + user.getPhone());
			System.out.println("Role ID: " + user.getRoleid());
		} else {
			System.out.println("Login thất bại. Username hoặc password không đúng.");
		}
	}
}
