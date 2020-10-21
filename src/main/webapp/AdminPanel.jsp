<%@page
	import="com.aws.codestar.projecttemplates.databaselayer.FileDetailsFromDatabase"%>
<%@page
	import="com.aws.codestar.projecttemplates.databaselayer.AdminLoginDatabase"%>
<%@page import="com.aws.codestar.projecttemplates.pojo.DatabaseFilePojo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Box as a service</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
</head>
<!--
@author Anisha Agarwal
-->
<body>

	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100">
					<header>
					<h1>Hello Admin!</h1>
					<br>
					</header>
					<table>
						<thead>
							<tr class="table100-head">
								<th>User</th>
								<th>File name</th>
								<th>Description</th>
								<th>Date created</th>
								<th>Delete</th>
							</tr>
						</thead>
						<%
							AdminLoginDatabase info = new AdminLoginDatabase();
							ArrayList<DatabaseFilePojo> userList = info.adminLogin();
							for (int i = 0; i < userList.size(); i++) {
								DatabaseFilePojo list = (DatabaseFilePojo) userList.get(i);
								System.out.println("helloooo: " + list.getFirstusername() + "/" + list.getFileName());
						%>

						<tbody>
							<tr>
								<td><%=list.getFirstusername()%></td>
								<td><%=list.getFileName()%></td>
								<td><%=list.getFileDescription()%></td>
								<td><%=list.getFileUploadTime()%></td>
								<td>
									<form action="DeleteControllerForAdmin" method="post">
										<input type="submit" class="proj281" value="Delete"> <input
											type="hidden" name="myObject"
											value=<%=list.getFirstusername() + "/" + list.getFileName()%> />
									</form>
								</td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					<br>
					<h3>
						<a href="Login.jsp">LogOut</a>
					</h3>
				</div>
			</div>
		</div>
	</div>

	<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/countdowntime/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>