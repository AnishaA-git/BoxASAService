<%@page
	import="com.aws.codestar.projecttemplates.databaselayer.FileDetailsFromDatabase"%>
<%@page import="com.aws.codestar.projecttemplates.pojo.DatabaseFilePojo"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
					<h2>
						Hello&nbsp;<%=session.getAttribute("firstusername")%>
					</h2>
					<br>
					<%
						String s = session.getAttribute("firstusername").toString();
						session.setAttribute("firstusername", s);
					%> </header>


					<table>
						<thead>
							<tr class="table100-head">
								<th>User</th>
								<th>File name</th>
								<th>Description</th>
								<th>Date created</th>
								<th>Download</th>
								<th>Delete</th>
								<th>Update</th>
							</tr>
						</thead>
						<%
							String firstusername = session.getAttribute("firstusername").toString();

							System.out.println("<showmylistoffilesjsp>username  " + firstusername);
							ArrayList<DatabaseFilePojo> pojo = new ArrayList<DatabaseFilePojo>();
							FileDetailsFromDatabase info = new FileDetailsFromDatabase();
							pojo = info.fetchData(firstusername);

							for (int i = 0; i < pojo.size(); i++) {
								DatabaseFilePojo object = new DatabaseFilePojo();
								object = pojo.get(i);
								String fn = object.getFileName();
								System.out.println("object  " + object.getFileName());
								request.setAttribute("filename", fn);
						%>
						<tbody>
							<tr>
								<th><%=object.getFirstusername()%></th>
								<th><%=object.getFileName()%></th>
								<th><%=object.getFileDescription()%></th>
								<th><%=object.getFileUploadTime()%></th>
								<td>
									<form action="DownloadController" method="post">
										<input type="submit" class="proj281" value="Download">
										<input type="hidden" name="myObject"
											value=<%=object.getFileName()%> />
									</form>
								</td>
								<td>
									<form action="DeleteController" method="post">
										<input type="submit" class="proj281" value="Delete"> <input
											type="hidden" name="myObject" value=<%=object.getFileName()%> />
									</form>
								</td>
								<td>
									<form action="MainMenuController" method="post"
										enctype="multipart/form-data">
										<a data-toggle="tooltip" data-placement="bottom" 
										title="The file name should not have spaces.">
										<input type="file" class="proj281" id="file2" name="file"
											multiple></a><input type="submit" class="proj281"
											value="Submit" id="btnSubmit" />
									</form>
								</td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					<br>

					<form id="myform" action="MainMenuController"
						onsubmit="return myFunction();" method="post"
						enctype="multipart/form-data">
									
							<label for="file">Choose file to upload</label> 
						<a data-toggle="tooltip" data-placement="bottom"
						title="The file name should not have spaces.">
						<input type="file"
							class="proj281" id="file" name="file" multiple> <br>
						</a>
						<input type="submit" class="proj281" value="Submit" id="btnSubmit" />

					</form>
					<br>

					<script>
            function myFunction()
            {

                var fileUpload = document.getElementById('file');
                var flag = false;

                if (typeof (fileUpload.files) !== "undefined")
                {
                    for (var i = 0; i < fileUpload.files.length; i++)
                    {
                        var maxsize = 10 * 1024;

                        var size = fileUpload.files[i].size / 1024;

                        if (size > maxsize)
                        {
                            alert("please reload files size of some files less than 10 MB");
                            document.getElementById("myform").reset();
                            flag = false;


                        } else
                        {
                            flag = true;


                        }

                    }
                } else
                {

                    alert("This browser does not support HTML5.");
                }
                return flag;
            }


        </script>
					<a href="Login.jsp"><h2>LogOut</h2></a>
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
	<script>
		$(document).ready(function() {
			$('[data-toggle="tooltip"]').tooltip();
		});
	</script>
</body>
</html>
