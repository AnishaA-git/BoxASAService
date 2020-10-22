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
			String dbName = System.getProperty("RDS_DB_NAME");
			String userName = System.getProperty("RDS_USERNAME");
			String password = System.getProperty("RDS_PASSWORD");
			String hostname = System.getProperty("JDBC_CONNECTION_STRING");
			String port = System.getProperty("RDS_PORT");
			String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password="
					+ password;

			con = DriverManager.getConnection(jdbcUrl);
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
