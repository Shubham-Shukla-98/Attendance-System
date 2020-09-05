<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@ page import= "java.sql.*"%>
<% Class.forName("com.mysql.jdbc.Driver");%>
<html>
<head>
<title>Entering class into database</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style type="text/css">
<!--
.style1 {font-family: Verdana, Arial, Helvetica, sans-serif}
-->
        </style>
</head>
<body>
    <div class="style1">Entering class into database</div>
    <form method="POST" name="classinput" class="style1">
        <table border="0" width="217" cellspacing="2">
            <tbody>
                <tr>
                    <td width="124">Department : </td>
                    <td width="83"><select name="dept">
                            <option>CSE</option>
                            <option>MECH</option>
                            <option>IT</option>
                            <option>CIVIL</option>
                            <option>ECE</option>
                            <option>ECS</option>
                            <option>CSIT</option>
                  </select></td>
                </tr>
                <tr>
                    <td>Semester :</td>
                    <td><select name="sem">
                            <option>1</option>
                            <option>3</option>
                            <option>5</option>
                            <option>7</option>
                        </select></td>
                </tr>
                <tr>
                    <td>Section :</td>
                    <td><select name="section">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                        </select></td>
                </tr>
                <tr>
                    <td></td>
                </tr>
            </tbody>
      </table>
    </form>
    <%!
	public class create
	{
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/deptwise";
		String user="root";
		String password="1234";
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		public create()
		{
			try
			{
				connection = DriverManager.getConnection(url,user,password);
				ps = connection.prepareStatement("create table ? (roll_no int,nam varchar(50),grup varchar(2),total int default 0,sem int,PRIMARY KEY (roll_no))");
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();	
			}
		}
		public int setup(String input)
		{
			int res = 0;
			try
                        {       ps.setString(1,input);
				int x = ps.executeUpdate();
				return x;
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			return 0;
		}
	}
%>
<%

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
		
		login s = new login();
		ResultSet r = s.setup(us, pw);
                if(r==null)
                kr = s.chkadmin(us, pw);
%>
</body>
</html>
