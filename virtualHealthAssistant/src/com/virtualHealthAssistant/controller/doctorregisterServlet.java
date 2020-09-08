package com.virtualHealthAssistant.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.virtualHealthAssistant.dao.doctorregisterDao;
import com.virtualHealthAssistant.model.doctorregister;
 
/**
 * Servlet implementation class doctorregisterServlet
 */
@WebServlet("/dregister")
public class doctorregisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private doctorregisterDao doctorDao=new doctorregisterDao();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doctorregisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/doctorviews/dregister.jsp");
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
		String emailid=request.getParameter("emailid");
		String phone=request.getParameter("phone");
		String DOB=request.getParameter("dob");
		String Department=request.getParameter("department");
		String Hospital=request.getParameter("hospital");
		String experience=request.getParameter("exp");
		String age=request.getParameter("age");
		String specialisation=request.getParameter("specialization");
		String sex=request.getParameter("sex");
		String license=request.getParameter("license");
		
		doctorregister doctor = new doctorregister();
		doctor.setFirstName(firstName);
		doctor.setLastName(lastName);
		doctor.setUsername(username);
		doctor.setPassword(password);
		doctor.setEmailid(emailid);
		doctor.setPhone(phone);
		doctor.setDOB(DOB);
		doctor.setDepartment(Department);
		doctor.setHospital(Hospital);
		doctor.setExperience(experience);
		doctor.setAge(age);
		doctor.setSpecialisation(specialisation);
		doctor.setSex(sex);
		doctor.setLicense(license);
		try {
			doctorDao.registerDoctor(doctor);
		}
		catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/doctorviews/doctorlogin.jsp");
		dispatcher.forward(request, response);
	}

}
