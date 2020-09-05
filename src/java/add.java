
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shubham Shukla
 */
public class add extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            String dept = request.getParameter("branch");
            String name = request.getParameter("name");
            int sem = Integer.parseInt(request.getParameter("sem"));
            int sec = Integer.parseInt(request.getParameter("section"));
            int roll = Integer.parseInt(request.getParameter("roll_no"));
            String gr = request.getParameter("group");

            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/deptwise";
            String user = "root";
            String password = "1234";
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            out.println("checking");
            String q1 = "select * from classes where dept = '" + dept + "' and sem =" + sem + " and section = " + sec;
            ResultSet rs = statement.executeQuery(q1);

            if (rs.next()) {
                String query = "insert into " + dept + " values(" + roll + ",'" + name + "','" + sec + "'," + sem + ",'"
                        + gr + "',0)";
                int x = statement.executeUpdate(query);
                out.println("student inserted<br>");
            } else {
                out.println("Class not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
