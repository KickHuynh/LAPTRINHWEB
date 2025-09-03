package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.models.UserModel;

public interface UserDao {

	// Lay het User
	List<UserModel> findAll();

	// Lay 1 user
	UserModel findById(int id);

	// Them user (register)
	void insert(UserModel user);

	UserModel get(String username);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean checkExistPhone(String phone);
	
	UserModel getByEmail(String email);
	boolean updatePassword(UserModel user);


}
