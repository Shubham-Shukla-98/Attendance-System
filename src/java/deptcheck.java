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
public class deptcheck extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
        response.setContentType("text/html;charset=UTF-8");
        try{
            PrintWriter out = response.getWriter();
            out.println("</html>");
            String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/deptwise";
		String user="root";
		String password="1234";
                
                int a,b,c;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
                Class.forName("com.mysql.jdbc.Driver");
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
                String de = resultSet.getString(1);
                request.getSession().setAttribute("dept", de);
                }
                else{
                request.getSession().setAttribute("dept", null);
                }
                response.sendRedirect("login.jsp");
        }
        catch(IOException e)
        {
             e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(deptcheck.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(deptcheck.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
