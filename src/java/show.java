
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

public class show {
	int a, b, c;
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/deptwise";
	String user = "root";
	String password = "1234";
	String x = new String();
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet resultSet = null;
	int y, z;
	String query = new String();
	Statement statement;

	public show() {
		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
		} catch (Exception e) {
			a = 10;
		}
	}

	public ResultSet setup(String u, int p, int g1) {
		int res = 0;
		try {
			x = u;
			y = p;
			z = g1;
			query = "select roll_no , nam from " + x + " where sem = " + y + " and sec = " + z;
			resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (Exception e) {
			b = 10;
		}
		return resultSet;
	}
}