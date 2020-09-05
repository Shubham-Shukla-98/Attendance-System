
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

/**
 *
 * @author Shubham Shukla
 */
public class redir extends HttpServlet {

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
            String sem = request.getParameter("sem");
            String sec = request.getParameter("section");
            String sub = request.getParameter("subject");
            request.getSession().setAttribute("sem", sem);
            request.getSession().setAttribute("section", sec);
            request.getSession().setAttribute("subject", sub);

            out.println("sem : " + sem);
            out.println("section : " + sec);

            if (request.getParameter("today") != null) {
                response.sendRedirect("show.jsp");
                out.println("today");
            } else if (request.getParameter("exist") != null) {
                response.sendRedirect("exist.jsp");
                out.println("exist");
            } else if (request.getParameter("report") != null) {
                response.sendRedirect("report.jsp");
                out.println("report");
            } else if (request.getParameter("monthly") != null) {
                response.sendRedirect("monthly_report.jsp");
                out.println("insert");
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
