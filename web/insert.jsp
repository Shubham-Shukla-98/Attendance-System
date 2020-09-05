<%-- 
    Document   : show
    Created on : Sep 16, 2017, 9:21:39 PM
    Author     : Shubham Shukla
--%>
<%@ page import= "java.sql.*"%>
<%@page  import = "java.util.Calendar"%>
<%@page  import = "java.util.Date"%>
<% Class.forName("com.mysql.jdbc.Driver");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Attendance</title>
        <script type="text/javascript">
		var count = 1;
        function SelectALL()
        {
            var eleNodelist = document.getElementsByTagName("input");
            for (i = 0; i < eleNodelist.length; i++) {
                if (eleNodelist[i].type == 'checkbox' && eleNodelist[i].id!='chkall')
		{
			if(count%2!=0)
                        	eleNodelist[i].checked =true;
                 	else
				eleNodelist[i].checked =false;
			 }			 
		}
		count++;
        }
    </script>
    </head>
    <body>
    <%!
            public class show
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
		public show()
		{
			try
			{
				connection = DriverManager.getConnection(url,user,password);
				statement = connection.createStatement();
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
	}
            
        %>
<%

		String c = new String();
                int s=0,q=0;
                ResultSet x=null;
		
		if(session.getAttribute("dept")!=null)
		{
			c = (String)session.getAttribute("dept");
		}
		if(session.getAttribute("sem")!=null)
		{
			s = Integer.parseInt((String)session.getAttribute("sem"));
		}
                if(session.getAttribute("section")!=null)
		{
			q = Integer.parseInt((String)session.getAttribute("section"));
		}
                show sh = new show();
                ResultSet kx = sh.setup(c,s,q);
                
%>
<form action="insertt.jsp" method="POST">
<table border="1">
    <input type = "date" name = "date">
     Subject :
    <select name="subject">
        <option>SELECT SUBJECT</option>
        <option>TOC</option>
        <option>POS</option>
        <option>CG</option>
        <option>MMT</option>
        <option>WD</option>
        <option>MP</option>                         
    </select>    
    <tbody>
        <%=java.time.LocalDate.now()%>
        <tr>
            <td>Roll no</td>
            <td>Name </td>
            <td>Status <input type="checkbox" id="chkall" value="checkbox" onchange="javascript:SelectALL()"/></td>
        </tr>
        <% int i=0;
                while(kx.next()){%>
        <tr>
            <td><%=kx.getString(1)%></td>
            <td><%=kx.getString(2)%></td>
            <td><input type="checkbox" name="<%=kx.getString(1)%>"/></td>
        </tr>
        <%} %>
    </tbody>
    <input type="submit" value="Submit Attendance"/>
    
</table>
</form>
    <a href="exist.jsp">update existing record</a>
    </body>
</html>
