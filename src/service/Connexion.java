package service;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
	
	public static Connection getConnection()throws Exception{
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/javadb?characterEncoding=latin1&autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC";
			String username = "root";
			String password = "";

			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connection à la base de données");
			return conn;
		
		}catch (Exception e) {e.printStackTrace();}
	
		return null;
	}
}
