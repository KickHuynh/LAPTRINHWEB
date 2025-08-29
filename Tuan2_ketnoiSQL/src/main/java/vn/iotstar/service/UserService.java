package vn.iotstar.service;

import vn.iotstar.model.User;

public interface UserService {
    User login(String username, String password);
    User get(String username);
    
    public boolean register(String username, String password, String email, String fullname, String phone);
    
    public boolean checkExistEmail(String email);
    public boolean checkExistUsername(String username);
    public boolean checkExistPhone(String phone);
    public void insert(User user);
    
    
    
    
    
}
