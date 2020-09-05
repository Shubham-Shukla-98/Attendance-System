
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shubham Shukla
 */
public class create extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/deptwise";
                String user = "root";
                String password = "1234";

                try {
                        PrintWriter out = response.getWriter();
                        String de = request.getParameter("dept");
                        String sem = request.getParameter("sem");
                        String sec = request.getParameter("section");

                        Class.forName("com.mysql.jdbc.Driver");
                        Connection connection = DriverManager.getConnection(url, user, password);
                        Statement statement = connection.createStatement();

                        String insert = "insert into classes values ('" + de + "'," + sem + "," + sec + ")";
                        int x1 = statement.executeUpdate(insert);
                        out.println("class added");

                        String query = "create table " + de
                                        + " (roll_no int,nam varchar(50),sec int,sem int,grup varchar(2),total int default 0,PRIMARY KEY (roll_no))";
                        int x = statement.executeUpdate(query);
                        out.println("class student table created");

                        String total = "create table " + de + "_total (roll_no int,FOREIGN KEY (roll_no) REFERENCES "
                                        + de + "(roll_no),sub varchar(50),total int)";
                        int x3 = statement.executeUpdate(total);
                        out.println("total record table created<br>");

                        String create = "create table " + de + "_record (roll_no int,FOREIGN KEY (roll_no) REFERENCES "
                                        + de
                                        + "(roll_no),dat date,sub varchar(50),att boolean default false,UNIQUE KEY (roll_no,dat,sub))";
                        int x2 = statement.executeUpdate(create);
                        out.println("class record table created");

                } catch (SQLException e) {
                        e.printStackTrace();
                } catch (ClassNotFoundException ex) {
                        Logger.getLogger(create.class.getName()).log(Level.SEVERE, null, ex);
                }

        }
}
