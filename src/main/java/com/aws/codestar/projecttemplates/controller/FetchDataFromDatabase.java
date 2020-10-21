
package com.aws.codestar.projecttemplates.controller;

import com.aws.codestar.projecttemplates.databaselayer.FileDetailsFromDatabase;
import com.aws.codestar.projecttemplates.pojo.DatabaseFilePojo;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*
* @author Anisha Agarwal
*/
public class FetchDataFromDatabase extends HttpServlet {

	/**
	 * @author Anisha Agarwal
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<DatabaseFilePojo> ObjectOfDatabaseFile;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstusername;
		String showMyListOfFiles = "showMyListOfFiles.jsp";
		firstusername = request.getSession(false).getAttribute("firstusername").toString();
		FileDetailsFromDatabase show = new FileDetailsFromDatabase();
		ObjectOfDatabaseFile = show.fetchData(firstusername);
		request.setAttribute("DatabaseValues", ObjectOfDatabaseFile);
		RequestDispatcher dispatcher = request.getRequestDispatcher(showMyListOfFiles);
		dispatcher.forward(request, response);

	}

}
