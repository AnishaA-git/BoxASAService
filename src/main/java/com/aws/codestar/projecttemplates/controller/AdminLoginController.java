package com.aws.codestar.projecttemplates.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aws.codestar.projecttemplates.databaselayer.AdminLoginDatabase;
import com.aws.codestar.projecttemplates.pojo.DatabaseFilePojo;

public class AdminLoginController extends HttpServlet {

	/**
	 * @author Anisha Agarwal
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		AdminLoginDatabase admin = new AdminLoginDatabase();
		ArrayList<DatabaseFilePojo> userList = new ArrayList<DatabaseFilePojo>();
		userList = admin.adminLogin();
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPanel.jsp");
			dispatcher.forward(request, response);
		} catch (ServletException | IOException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside doGet");


	}
}
