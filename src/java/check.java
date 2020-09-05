/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shubham Shukla
 */
public class check extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.jdbc.Driver");
                String driver="com.mysql.jdbc.Driver";
                String url="jdbc:mysql://localhost:3306/deptwise";
                String user="root";
                String password="1234";
                
                int a,b,c;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
                connection = DriverManager.getConnection(url,user,password);
		ps = connection.prepareStatement("select dept from login where username = ? and pass = ?");
                
                String us = new String();
		String pw = new String();
                int kr=1;
		
		if(request.getParameter("uname")!=null)
		{
			us = request.getParameter("uname");
		}
		if(request.getParameter("psw")!=null)
		{
			pw = request.getParameter("psw");
		}
                
                ps.setString(1,us);
		ps.setString(2,pw);
		resultSet = ps.executeQuery();
                if(resultSet.next()){
                String aa = resultSet.getString(1);
                out.println(aa);
                
                String query = "select * from classes where dept = '"+aa+"'";
                Statement statement = connection.createStatement();
                ResultSet x = statement.executeQuery("query");
                if(x==null)
                {
                    out.println("error");
                }
                
		if(x.next())
                {
                    out.println(x.getString(1));
                    out.println(x.getInt(2));
                    out.println(x.getInt(3));
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(check.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
