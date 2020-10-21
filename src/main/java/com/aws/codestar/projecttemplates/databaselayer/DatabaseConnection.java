package com.aws.codestar.projecttemplates.databaselayer;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
*
* @author Anisha Agarwal
*/

public class DatabaseConnection {

	public Connection makeConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/281-project-1","root", "hello");
			//con = DriverManager.getConnection("jdbc:mysql://database-2.cefaiucwkkve.us-west-1.rds.amazonaws.com:3306/test","root","testadmin");

			if (con != null)
				System.out.println("connection successful");
			else
				System.out.println("connection failure");

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Issue is" + e);
		}
		return con;
	}

	public void closeConnection(Connection con) {
		if (con != null)
			try {
				con.close();
			} catch (SQLException ex) {
				Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
			}
	}

}
