
package com.aws.codestar.projecttemplates.databaselayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anisha Agarwal
 */
public class DeleteFileFromDatabase {

	public void deleteFile(String filename) {

		Connection connectionObject = null;
		PreparedStatement statementObject0 = null;
		PreparedStatement statementObject = null;
		ResultSet resultSetObject = null;

		String query0 = "SET SQL_SAFE_UPDATES = 0";
		String query = "delete from filedetails where filename=?";

		DatabaseConnection databaseObject = new DatabaseConnection();
		connectionObject = databaseObject.makeConnection();

		if (connectionObject != null) {
			try {
				statementObject0 = connectionObject.prepareStatement(query0);
				statementObject0.executeUpdate();

				statementObject = connectionObject.prepareStatement(query);
				statementObject = connectionObject.prepareStatement(query);
				statementObject.setString(1, filename);
				statementObject.executeUpdate();

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

	}

}
