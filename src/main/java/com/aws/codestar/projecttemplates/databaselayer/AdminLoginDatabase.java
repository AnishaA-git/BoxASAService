
package com.aws.codestar.projecttemplates.databaselayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aws.codestar.projecttemplates.pojo.DatabaseFilePojo;

/**
 *
 * @author Anisha Agarwal
 */
public class AdminLoginDatabase {

	public ArrayList<DatabaseFilePojo> adminLogin() {
		Connection connectionObject = null;
		PreparedStatement statementObject = null;
		ResultSet resultSetObject = null;
		ArrayList<DatabaseFilePojo> userList = new ArrayList<DatabaseFilePojo>();

		String query = "select * from filedetails;";

		DatabaseConnection databaseObject = new DatabaseConnection();
		connectionObject = databaseObject.makeConnection();

		if (connectionObject != null) {
			try {
				statementObject = connectionObject.prepareStatement(query);
				resultSetObject = statementObject.executeQuery();

				while (resultSetObject.next()) {
					DatabaseFilePojo user = new DatabaseFilePojo();
					user.setFileName(resultSetObject.getString(1));
					user.setFileDescription(resultSetObject.getString(2));
					user.setFileUploadTime(resultSetObject.getString(3));
					user.setFirstusername(resultSetObject.getString(4));
					userList.add(user);
				}
			} catch (SQLException ex) {
				System.out.println("Exception is:" + ex);
			} finally {

				try {
					if (resultSetObject != null)
						resultSetObject.close();
					if (statementObject != null)
						statementObject.close();
					if (connectionObject != null)
						databaseObject.closeConnection(connectionObject);

				} catch (SQLException ex) {
					Logger.getLogger(LoginDatabase.class.getName()).log(Level.SEVERE, null, ex);
				}

			}

		}

		return userList;
	}
}
