 	 	package com.store.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
	private static final String url = "jdbc:mysql://127.0.0.1:3306/db_store?useUnicode=true&characterEncoding=utf-8";
	private static final String username = "root";
	private static final String password = "654321";
	private static Connection conn = null;
	
	public static void jdbc() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		jdbc();
		if (conn == null) {
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		}
		return conn;
	}

}
