package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/shopping_mall?serverTimezone=UTC";
			String id ="root";
			String pw ="gkqrur1!1531a";
			
			conn = DriverManager.getConnection(url,id,pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
