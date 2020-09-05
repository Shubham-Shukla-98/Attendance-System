
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
public class change extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {

            PrintWriter out = response.getWriter();
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/deptwise";
            String user = "root";
            String password = "1234";
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            String dept = (String) request.getSession().getAttribute("dept");
            int r = Integer.parseInt(request.getParameter("roll_no"));
            String date = request.getParameter("dat");
            String sub = request.getParameter("subject");
            String news = request.getParameter("subject");
            String st = request.getParameter("status");
            int stat, org = 0;
            out.println("dept : " + dept);
            if (st.equals("present")) {
                stat = 1;
            } else
                stat = 0;

            out.println("roll : " + r);
            out.println("subject : " + sub);
            out.println("date : " + date);

            String q1 = "select att from " + dept + "_record where roll_no = " + r + " and dat = '" + date
                    + "' and sub = '" + sub + "'";
            ResultSet rs = statement.executeQuery(q1);
            if (rs.next()) {
                org = Integer.parseInt(rs.getString(1));
                out.println("status : " + rs.getString(1));
            } else {
                out.println("not avl");
            }
            if (stat != org) {
                int tot = 0;
                String q2 = "update " + dept + "_record set att = " + stat + " where roll_no = " + r + " and dat = '"
                        + date + "' and sub = '" + sub + "'";
                int x = statement.executeUpdate(q2);
                out.println("changed : " + x);
                String q3 = "select total from " + dept + "_total where roll_no =" + r + " and sub = '" + sub + "';";
                ResultSet y = statement.executeQuery(q3);
                if (y.next()) {
                    tot = Integer.parseInt(y.getString(1));
                    if (stat == 1 && org == 0)
                        tot = tot + 1;
                    if (stat == 0 && org == 1)
                        tot = tot - 1;
                    String q4 = "update " + dept + "_total set total =" + tot + " where roll_no = " + r + " and sub = '"
                            + sub + "'";
                    int ss = statement.executeUpdate(q4);
                    out.println("lines updated : " + ss);
                }
            } else {
                out.println("No change needed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
