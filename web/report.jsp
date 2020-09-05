<%-- 
    Document   : report
    Created on : Sep 27, 2017, 1:38:42 PM
    Author     : Shubham Shukla
--%>

<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Monthly Report</title>
    </head>
    <body>
        <h1>Monthly Report</h1>
        <%!
            public class show
	{
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/deptwise";
		String user="root";
		String password="1234";
		
		Connection connection = null;
		ResultSet resultSet = null;
                Statement statement = null; 
                Statement statement1 = null;  
                Statement statement2 = null; 
                int i=0;
                             
		
		public show()
		{
			try
			{
				connection = DriverManager.getConnection(url,user,password);
                                statement = connection.createStatement();
                                statement1 = connection.createStatement();
                                statement2 = connection.createStatement();
                        }
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
                
                public ResultSet get(int sem,int sec,String dep)
                {
                    try
                    {
                        String sa = "select roll_no from "+dep+" where sem = "+sem+" and sec = "+sec;
                        ResultSet xx = statement.executeQuery(sa);
                        return xx;
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    return null;
                }
                
		public ResultSet setup(int sem,int sec,String sub,int roll,String dep)
		{
			try
			{	String ps = " select a.roll_no,a.nam,b.dat,b.att from "+dep+" a, "+dep+"_record b where a.roll_no=b.roll_no and a.sem = "+sem+" and a.sec = "+sec+" and sub = '"+sub+"' and a.roll_no = "+roll;
                                resultSet = statement1.executeQuery(ps);
				return resultSet;
			}
			catch(SQLException e)
			{
                            e.printStackTrace();
			}
                        return resultSet;
		}
	}
            
        %>
<%

		String c = new String();
                String g = new String();
                int sem;
                ResultSet x=null;
		c = (String)session.getAttribute("dept");
		int s = Integer.parseInt((String)session.getAttribute("sem"));
		int q = Integer.parseInt((String)session.getAttribute("section"));
                g = (String)session.getAttribute("subject");
                
                show sh = new show();
                ResultSet nm = sh.get(s,q,c);
                while(nm.next())
                {
                    int roll = Integer.parseInt(nm.getString(1));
                    ResultSet disp = sh.setup(s,q,g,roll,c);
                    int j=0;
                    int total=0;
                    int present=0;
                    if(sh.i==0)
                    {
                        sh.i++;
        %>
                                <table border="1">    
                                    <tbody>
                                        <tr>
                                        <td>Roll no</td>
                                        <td>Name </td>
        
        <%
                        while(disp.next())
                        {
                            %>
                                        
                                        <td><%=disp.getString(3)%></td>
        
        <%
                        }
                        disp.beforeFirst();
                        %>
                        <td>Total </td>
                        <td>Percentage </td>
                                        </tr>
             
        <%
                    }
                     while(disp.next())
                        {String st;
                         
                            if(j==0)
                            {
        %>
                                        <tr>
                                        <td><%=disp.getString(1)%></td>
                                        <td><%=disp.getString(2)%></td>
        <%
                            j++;
                            }
        %>
        
                                        <td><%if(disp.getString(4).equals("0")){st = "absent";}else{st = "present"; present++;} total++;%><%=st%></td>
        
        <%
                        }
        %>
        <td><%=present%>/<%=total%> </td>
        <td><%=(present*100/total)%>%</td>
                                        </tr>
        
        <%
                }
%>

    </tbody>
</table>
        
    </body>
</html>
