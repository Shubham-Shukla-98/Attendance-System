import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Scanner;

public class cre {
	public static void main(String[] args) {

		try {

			// API:Database
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/deptwise", "root",
						"1234");

				String query = "create table 3_CSE_2 (roll_no int,nam varchar(50),grup varchar(2),total int default 0,sem int,PRIMARY KEY (roll_no))";
				Statement statement = connection.createStatement();

				System.out.println("1");
				statement.executeUpdate(query);

				System.out.println("2");
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
