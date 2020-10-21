
package com.aws.codestar.projecttemplates.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aws.codestar.projecttemplates.databaselayer.RegistrationDatabase;

public class RegistrationController extends HttpServlet {
	/**
	 * @author Anisha Agarwal
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstusername;
		String lastusername;
		String password;
		boolean flagForRegistration = false;
		String loginPage = "Login.jsp";

		HttpSession ss = request.getSession(true);

		firstusername = request.getParameter("firstusername").toString();
		lastusername = request.getParameter("lastusername").toString();
		password = request.getParameter("password").toString();

		RegistrationDatabase databaseObject = new RegistrationDatabase();
		databaseObject.register(firstusername, lastusername, password);

		if (flagForRegistration)
			ss.setAttribute("registerFlag", "Registration successful go to log in page");
		else
			ss.setAttribute("registerFlag",
					"Registration failed username already present,please try again with diffrent username");

		RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
		dispatcher.forward(request, response);

	}

}
