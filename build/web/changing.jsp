<%-- 
    Document   : changing
    Created on : Oct 13, 2017, 10:12:50 AM
    Author     : Shubham Shukla
--%>
<%@page import="java.sql.*"%>
<%Class.forName("com.mysql.jdbc.Driver");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
        try{
            
            String driver="com.mysql.jdbc.Driver";
            String url="jdbc:mysql://localhost:3306/deptwise";
            String user="root";
            String password="1234";
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            
            String dept = (String)request.getSession().getAttribute("dept");
            int r = Integer.parseInt(request.getParameter("roll_no"));
            String date = request.getParameter("dat");
            String sub = request.getParameter("subject");
            String news = request.getParameter("subject");
            String st = request.getParameter("status");
            int stat,org = 0;
            if(st.equals("present"))
            {
                stat=1;
            }
            else
                stat = 0;
            
           
            String q1 = "select att from "+dept+"_record where roll_no = "+r+" and dat = '"+date+"' and sub = '"+sub+"'";
            ResultSet rs = statement.executeQuery(q1);
            
            org = Integer.parseInt(rs.getString(1));
            
            if(stat!=org)
            {
                int tot=0;
                String q2 = "update "+dept+"_record set att = "+stat+" where roll_no = "+r+" and dat = '"+date+"' and sub = '"+sub+"'";
                int x = statement.executeUpdate(q2);
                String q3 = "select total from "+dept+"_total where roll_no ="+r+" and sub = '"+sub+"';";
                ResultSet y = statement.executeQuery(q3);
                if(y.next())
                {
                    tot = Integer.parseInt(y.getString(1));
                    if(stat==1 && org==0)
                        tot=tot+1;
                    if(stat==0 && org ==1)
                        tot=tot-1;
                    String q4 = "update "+dept+"_total set total ="+tot+" where roll_no = "+r+" and sub = '"+sub+"'";
                    int ss = statement.executeUpdate(q4);%>

                    Record Updated 
<%
                }
            }
            else{
                %>
                No Record Updated
<%
            }
            
            	
        
        }catch(Exception e)
        {
            e.printStackTrace();
        }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
