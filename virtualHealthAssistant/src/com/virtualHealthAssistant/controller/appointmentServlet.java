package com.virtualHealthAssistant.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtualHealthAssistant.dao.appointmentDao;
import com.virtualHealthAssistant.model.appointment;

/**
 * Servlet implementation class appointmentServlet
 */
@WebServlet("/appointment")
public class appointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private appointmentDao appdao=new appointmentDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public appointmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/patientviews/appointmen.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String doctorName=request.getParameter("doctor");
		String patientName=request.getParameter("patient");
		String phone=request.getParameter("phone");
		String date=request.getParameter("date");
		String time=request.getParameter("time");
		String service=request.getParameter("service");
		appointment app=new appointment();
		app.setDoctorName(doctorName);
		app.setPatientName(patientName);
		app.setDate(date);
		app.setPhone(phone);
		app.setTime(time);
		app.setService(service);
		try {
			if(appdao.validateapp(app)) {
				RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/patientviews/appointmen.jsp");
				dispatcher.forward(request, response);
			}
			else {
				appdao.bookappointment(app);
				RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/patientviews/availability.jsp");
				dispatcher.forward(request, response);
			}
	}
		catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	}
}
}
