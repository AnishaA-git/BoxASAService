package com.aws.codestar.projecttemplates.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aws.codestar.projecttemplates.databaselayer.LoginDatabase;

public class LoginController extends HttpServlet {

	/**
	 * @author Anisha Agarwal
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		String firstusername = null;
		String lastusername = null;
		String password = null;
		boolean flagForLogin = false;
		String mainMenuPage = "MainMenu.jsp";
		String loginPage = "Login.jsp";
		String message = "invalid username/password";

		firstusername = request.getParameter("firstusername").toString();
		lastusername = request.getParameter("lastusername").toString();
		password = request.getParameter("password").toString();

		LoginDatabase loginObject = new LoginDatabase();
		flagForLogin = loginObject.login(firstusername, lastusername, password);
		if (firstusername.equals("Admin")) {
			AdminLoginController adminUser = new AdminLoginController();
			adminUser.doPost(request, response);
		} else if (flagForLogin) {
			HttpSession session = request.getSession(true);
			session.setAttribute("firstusername", firstusername);

			try {
				RequestDispatcher dispatcher = request.getRequestDispatcher("MainMenu.jsp");
				dispatcher.forward(request, response);
			} catch (ServletException | IOException ex) {
				Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			System.out.println("unsuccessful login::::");

			RequestDispatcher dispatcher2 = request.getRequestDispatcher("Login.jsp");
			request.setAttribute("invalid username/password", message);

			try {
				dispatcher2.forward(request, response);
			} catch (ServletException ex) {
				Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
			}

		}

	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside doGet");
	}
}
