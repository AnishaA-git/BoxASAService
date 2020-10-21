
package com.aws.codestar.projecttemplates.databaselayer;

import com.aws.codestar.projecttemplates.pojo.DatabaseFilePojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anisha Agarwal
 */
public class FileDetailsFromDatabase {

	public ArrayList<DatabaseFilePojo> fetchData(String firstusername) {

		Connection connectionObject = null;
		PreparedStatement statementObject = null;
		ResultSet resultSetObject = null;
		ArrayList<DatabaseFilePojo> pojo = new ArrayList<DatabaseFilePojo>();
		DatabaseFilePojo pojoObject;
		
		String query = "select * from filedetails where firstusername=?";

		DatabaseConnection databaseObject = new DatabaseConnection();
		connectionObject = databaseObject.makeConnection();

		if (connectionObject != null) {
			try {
				statementObject = connectionObject.prepareStatement(query);
				statementObject.setString(1, firstusername);
				resultSetObject = statementObject.executeQuery();

				while (resultSetObject.next()) {
					pojoObject = new DatabaseFilePojo();
					String file_name = resultSetObject.getString("filename");
					String file_upload_time = resultSetObject.getString("uploadtime");
					String file_description = resultSetObject.getString("description");
					String user_name = resultSetObject.getString("firstusername");
					pojoObject.setFileName(file_name);
					pojoObject.setFileDescription(file_description);
					pojoObject.setFileUploadTime(file_upload_time);
					pojoObject.setFirstusername(user_name);
					pojo.add(pojoObject);
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
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
		return pojo;

	}

}
