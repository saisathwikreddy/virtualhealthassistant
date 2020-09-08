package com.virtualHealthAssistant.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtualHealthAssistant.dao.patientregisterDao;
import com.virtualHealthAssistant.model.patientregister;
/**
 * Servlet implementation class patientregisterServlet
 */
@WebServlet("/pregister")
public class patientregisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private patientregisterDao patientDao=new patientregisterDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public patientregisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/patientviews/pregister.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName=request.getParameter("firstname");
		String lastName=request.getParameter("lastname");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String emailid=request.getParameter("email");
		String phone=request.getParameter("phone");
		String DOB=request.getParameter("dob");
		String age=request.getParameter("age");
		String sex=request.getParameter("sex");
		String bloodgroup=request.getParameter("bloodgroup");
		
		patientregister patient = new patientregister();
		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		patient.setUsername(username);
		patient.setPassword(password);
		patient.setEmailid(emailid);
		patient.setPhone(phone);
		patient.setDOB(DOB);
		patient.setAge(age);
		patient.setSex(sex);
		patient.setBloodgroup(bloodgroup);
		
		try {
			patientDao.registerPatient(patient);
		}
		catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
		}
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/patientviews/patientlogin.jsp");
		dispatcher.forward(request, response);
	}

}
