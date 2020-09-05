
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

public class updates {
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
    Statement statement1;
    Statement statement2;
    Statement statement3;

    public updates() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement1 = connection.createStatement();
            statement2 = connection.createStatement();
            statement3 = connection.createStatement();
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

    public int updat(String d, int a, int att, String sub) {
        try {
            String Quer = "insert into " + d + "_record values(" + a + ",'" + java.time.LocalDate.now() + "','" + sub
                    + "'," + att + ")";
            int res = statement1.executeUpdate(Quer);
            if (att == 1) {
                if (res > 0) {
                    String q1 = "select total from " + d + "_total where roll_no =" + a + " and sub ='" + sub + "'";
                    ResultSet p = statement2.executeQuery(q1);
                    if (p.next()) {
                        int totl = Integer.parseInt(p.getString(1));
                        totl = totl + 1;
                        String q2 = "update " + d + "_total set total = " + totl + " where roll_no = " + a
                                + " and sub = '" + sub + "'";
                        statement3.executeUpdate(q2);
                        return totl;
                    } else {
                        String q2 = "insert into " + d + "_total values (" + a + ",'" + sub + "',1)";
                        int insert = statement2.executeUpdate(q2);
                        return 2;
                    }
                } else {
                    return -1;
                }
            }
        } catch (Exception e) {
            b = 10;
        }
        return 0;
    }
}
