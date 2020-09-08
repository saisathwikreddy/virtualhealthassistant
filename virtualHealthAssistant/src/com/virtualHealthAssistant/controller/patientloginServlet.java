package com.virtualHealthAssistant.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtualHealthAssistant.dao.patientloginDao;
import com.virtualHealthAssistant.model.patientlogin;

/**
 * Servlet implementation class patientloginServlet
 */
@WebServlet("/plogin")
public class patientloginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private patientloginDao patientDao=new patientloginDao();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public patientloginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/patientviews/patientlogin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		patientlogin patient = new patientlogin();
		patient.setUsername(username);
		patient.setPassword(password);
		
		try {
			if(patientDao.validate(patient)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				session.setAttribute("password", password);
//				RequestDispatcher dispatcher=request.getRequestDispatcher("patienthome.html");
//			dispatcher.forward(request, response);
				response.sendRedirect("patienthome.html");
				}
			else {
				RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/patientviews/patientlogin.jsp");
				dispatcher.forward(request, response);
			}
		}
		catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	}
	}
}
