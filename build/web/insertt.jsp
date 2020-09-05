<%-- 
    Document   : update
    Created on : Sep 24, 2017, 11:09:52 AM
    Author     : Shubham Shukla
--%>

<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.*"%>
<%@page  import = "java.util.Calendar"%>
<%@page  import = "java.util.Date"%>
<%Class.forName("com.mysql.jdbc.Driver");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Updating attendance</title>
    </head>
    <body><%!
            public class update
	{
                int a,b,c;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/deptwise";
		String user="root";
		String password="1234";
		String x = new String();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		int y,z;
                String query = new String();
                Statement statement;
                Statement statement1;
                Statement statement2;
                Statement statement3;
		public update()
		{
			try
			{
				connection = DriverManager.getConnection(url,user,password);
				statement = connection.createStatement();
                                statement1 = connection.createStatement();
                                statement2 = connection.createStatement();
                                statement3 = connection.createStatement();
			}
			catch(Exception e)
			{
				a=10;
			}
		}
		public ResultSet setup(String u,int p,int g1)
		{
			int res = 0;
			try
			{	x=u;
				y=p;
                                z=g1;
                                query ="select roll_no , nam from "+x+" where sem = "+y+" and sec = "+z;
                                resultSet = statement.executeQuery(query);
				return resultSet;
			}
			catch(Exception e)
			{
                            b=10;
			}
                        return resultSet;
		}
                
                public int updat(String d,int a,int att,String sub,String date)
                {
                try
                {
                    String Quer = "insert into "+d+"_record values("+a+",'"+date+"','"+sub+"',"+att+")";
                    int res = statement1.executeUpdate(Quer);
                    if(att==1)
                    {
                    if(res>0)
                    {
                        String q1="select total from "+d+"_total where roll_no ="+a+" and sub ='"+sub+"'";
                        ResultSet p = statement2.executeQuery(q1);
                        if(p.next())
                        {
                            int totl = Integer.parseInt(p.getString(1));
                            totl=totl+1;
                            String q2="update "+d+"_total set total = "+totl+" where roll_no = "+a+" and sub = '"+sub+"'";
                            statement3.executeUpdate(q2);
                            return totl;
                        }
                        else
                        {
                            String q2="insert into "+d+"_total values ("+a+",'"+sub+"',1)";
                            int insert = statement2.executeUpdate(q2);
                            return 2;
                        }
                    }
                    else
                    {
                        return -1;
                    }
                    }
                }
                catch(Exception e)
                {
                    b=10;
		}
                    return 0;
                }
	}
            
        %>
<%

		String c = new String();
                String sb = new String();
                Integer s=0,q=0,stop =0;
                ResultSet x=null;
                String da = new String();
		
		if(session.getAttribute("dept")!=null)
		{
			c = (String)session.getAttribute("dept");
                        sb = request.getParameter("subject");
                        da = request.getParameter("date");
                        if(sb.equals("SELECT SUBJECT"))
                        {
                            stop=1;
                        }
		}
		s = Integer.parseInt((String)session.getAttribute("sem"));
		q = Integer.parseInt((String)session.getAttribute("section"));
                update sh = new update();
                ResultSet kx = sh.setup(c,s,q);
                
                if(stop==1)
                {
                    %>Select a subject to proceed <a href = "login.jsp">go back</a><%                    
                }
                else{
                while(kx.next() && stop ==0)
                {
                int at;
                if(request.getParameter(kx.getString(1))==null){at = 0;}else{at = 1;};
                int h = sh.updat(c,Integer.parseInt(kx.getString(1)),at,sb,da);
                if(h==-1)
                {
                    %>Entry already made for same date and subject please update<a href = "login.jsp">go back</a><%    
                }
                }
                kx.beforeFirst();
                }
%>
<h1>Attendance :</h1>
<table>
    <tbody>
        <tr >
            <td>Roll no</td>
            <td>Name </td>
            <td>Status</td>
        </tr>
        <% int i=0;
            String stat;
        while(kx.next()){%>
            <tr>
            <td><%=kx.getString(1)%></td>
            <td><%=kx.getString(2)%></td>
            <td><%if(request.getParameter(kx.getString(1))==null){stat = "absent";}else{stat = "present";}%><%=stat%></td>
            
            </tr>
        <%} %>
    </tbody>
        <a href="login.jsp">Home</a>
    </body>
</html>
