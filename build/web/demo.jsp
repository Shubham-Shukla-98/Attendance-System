<%-- 
    Document   : demo
    Created on : Oct 25, 2017, 4:30:06 PM
    Author     : Shubham Shukla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import= "java.sql.*"%>
<%@page import="java.util.ArrayList"%>
<%@page  import = "java.util.Calendar"%>
<%@page  import = "java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%!
	public class login
	{
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/deptwise";
		String user="root";
		String password="1234";
                
                int a,b,c;
		
		Connection connection = null;
                Connection connection1 = null;
		PreparedStatement ps = null;
                PreparedStatement ps1 = null;
		ResultSet resultSet = null;
		ResultSet resultSet1 = null;
		
		public login()
		{
                    try
			{
                                Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/agri","root","1234");
				connection1 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/agri","root","1234");
				
				ps = connection.prepareStatement("select crop from crops where state = ? and season = ?");
                                ps1 = connection.prepareStatement("select rating from rec where state = ? and season = ? and crop = ?");
			}
			catch(Exception e)
			{
				c=10;
			}
		}
		public ResultSet setup(String u,String h)
		{
			try
			{
                                ps.setString(1,u);
                                ps.setString(2,h);
				resultSet = ps.executeQuery();
                                
				return resultSet;
			}
			catch(SQLException e)
			{
                            a=10;
                        }
			return null;
		}
                public ArrayList gett(String u,String h,ResultSet a)
                {
                    try
			{
                                ArrayList<String> rece = new ArrayList<String>();
                                ArrayList<Integer> suc = new ArrayList<Integer>();
				while(a.next())
				{
					rece.add(a.getString(1));
				}
				for(int i=0;i<rece.size();i++)
				{
                                        ps1.setString(1,u);
                                        ps1.setString(2,h);
                                        ps1.setString(3,rece.get(i));
                                        ResultSet r1 = ps1.executeQuery();
					int sum=0,x =0,count=0;
					while(r1.next())
					{
						x = Integer.parseInt(r1.getString(1));
						sum=sum+x;
						count++;
					}
					suc.add((sum)*100/(5*count));
				}
				return suc;
			}
			catch(SQLException e)
			{
                            e.printStackTrace();
                        }
			return null;
                }
%>
<%

		String us = new String();
		if(session.getAttribute("state")!=null)
		{
			us = (String)session.getAttribute("state");
		}
                String season = null;
                int mon = Calendar.getInstance().get(Calendar.MONTH)+1;
		if(mon>=6 && mon<=9)
		{
			season = "Kharif";
		}
		if(mon<=3 || mon >9)
		{
			season = "Rabi";
		}
		
		login s = new login();
		ResultSet r = s.setup(us,season);
                ArrayList a1 = s.gett(us,season,r);
                
                
%>
<% Class.forName("com.mysql.jdbc.Driver");%>
    <body>
        <h1>Hello World!</h1>
        <%while(r.next()){%>
        crop : <%=r.getString(1)%>
        <%}%>
        
        <%for(int i=0;i<a1.size();i++){%>
        success : <%=a1.get(i)%>
        <%}%>
    </body>
</html>
