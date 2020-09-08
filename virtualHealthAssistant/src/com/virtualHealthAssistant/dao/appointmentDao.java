package com.virtualHealthAssistant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.virtualHealthAssistant.model.appointment;

public class appointmentDao {
	public int bookappointment(appointment app)throws ClassNotFoundException {
		String INSERT_USERS_SQL = "INSERT INTO appointments" +
	            " (doctor, patient, phone, service, date, time) VALUES " +
	            " (?, ?, ?, ?, ?, ?);";
	   
		int result=0; 
		Class.forName("com.mysql.jdbc.Driver");
		 try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtualhealthassitant","root","vssroma@123");

		            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
		            preparedStatement.setString(1, app.getDoctorName());
		            preparedStatement.setString(2, app.getPatientName());
		            preparedStatement.setString(3, app.getPhone());
		            preparedStatement.setString(4, app.getService());
		            preparedStatement.setString(5, app.getDate());
		            preparedStatement.setString(6, app.getTime());
		            
		            System.out.println(preparedStatement);
		            
		            result = preparedStatement.executeUpdate();

		        } catch (SQLException e) {
		            
		            printSQLException(e);
		        }

		return result;
	}
	public boolean validateapp(appointment app) throws ClassNotFoundException {
        boolean status = false;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/virtualhealthassitant", "root", "vssroma@123");
            PreparedStatement preparedStatement = connection
            .prepareStatement("select * from doctortable where doctor = ? and date = ?")) {
            preparedStatement.setString(1, app.getDoctorName());
            preparedStatement.setString(2, app.getDate());
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return status;
    }
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
