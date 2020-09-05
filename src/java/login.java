
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Shubham Shukla
 */
import java.sql.*;

public class login {
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/deptwise";
	String user = "root";
	String password = "1234";

	int a, b, c;

	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet resultSet = null;

	public login() {
		try {

			connection = DriverManager.getConnection(url, user, password);
			ps = connection.prepareStatement("select * from classes where dept = ?");
		} catch (SQLException e) {

			c = 10;
		}
	}

	public ResultSet setup(String u) {
		try {
			ps.setString(1, u);
			resultSet = ps.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			a = 10;

		}
		return null;
	}
}