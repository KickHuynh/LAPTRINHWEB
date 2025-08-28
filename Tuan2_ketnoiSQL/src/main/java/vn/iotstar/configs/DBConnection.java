package vn.iotstar.configs;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private final String serverName = "localhost";  // hoặc LAPTOP-U031V8FN\NGOCTAI
    private final String dbName = "ltwb";           // tên database bạn đã tạo
    private final String portNumber = "1433";
    private final String instance = "";   
    
    // tài khoản SQL Server (sa)
    private final String userID = "sa";             
    private final String password = "Ngoctai2005@";       // mật khẩu bạn đặt cho sa

    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber 
                + ";databaseName=" + dbName 
                + ";encrypt=true;trustServerCertificate=true";

        // Nếu có instance thì thêm instance vào connection string
        if (instance != null && !instance.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber
                    + ";databaseName=" + dbName 
                    + ";encrypt=true;trustServerCertificate=true";
        }

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }

    // Test kết nối
    public static void main(String[] args) {
        try {
            Connection conn = new DBConnection().getConnection();
            if (conn != null) {
                System.out.println("Kết nối SQL Server thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
