
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
public class LoginDatabase {

	public boolean login(String firstusername, String lastusername, String password) {
		boolean flagForLogin = false;
		Connection connectionObject = null;
		PreparedStatement statementObject = null;
		ResultSet resultSetObject = null;

		String query = "select * from user where firstusername=? and lastusername=? and password=?";

		DatabaseConnection databaseObject = new DatabaseConnection();
		connectionObject = databaseObject.makeConnection();

		if (connectionObject != null) {
			try {
				statementObject = connectionObject.prepareStatement(query);
				statementObject = connectionObject.prepareStatement(query);
				statementObject.setString(1, firstusername);
				statementObject.setString(2, lastusername);
				statementObject.setString(3, password);
				resultSetObject = statementObject.executeQuery();
				
				if (resultSetObject.next())
					flagForLogin = true;
				else
					flagForLogin = false;
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

		return flagForLogin;
	}
}
