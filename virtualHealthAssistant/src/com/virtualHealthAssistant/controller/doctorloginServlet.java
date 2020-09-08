package com.virtualHealthAssistant.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtualHealthAssistant.dao.doctorloginDao;
import com.virtualHealthAssistant.model.doctorlogin;

/**
 * Servlet implementation class doctorloginServlet
 */
@WebServlet("/dlogin")
public class doctorloginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private doctorloginDao doctorDao=new doctorloginDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doctorloginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/doctorviews/doctorlogin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		doctorlogin doctor = new doctorlogin();
		doctor.setUsername(username);
		doctor.setPassword(password);
		try {
			if(doctorDao.validate(doctor)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				response.sendRedirect("doctorhome.html");
				}
			else {
				RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/doctorviews/doctorlogin.jsp");
				dispatcher.forward(request, response);
			}
		}
		catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	}
	}
}
