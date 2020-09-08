package com.virtualHealthAssistant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.virtualHealthAssistant.model.doctorregister;

public class doctorregisterDao {
	public int registerDoctor(doctorregister doctor) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO doctortable" +
            " (emailid, firstname, lastname, DOB, sex, age, license, hospital, Department, specialisation, experience, phonenumber, username, password) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
   
        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        
        
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtualhealthassitant","root","vssroma@123");

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, doctor.getEmailid());
            preparedStatement.setString(2, doctor.getFirstName());
            preparedStatement.setString(3, doctor.getLastName());
            preparedStatement.setString(4, doctor.getDOB());
            preparedStatement.setString(5, doctor.getSex());
            preparedStatement.setString(6, doctor.getAge());
            preparedStatement.setString(7, doctor.getLicense());
            preparedStatement.setString(8, doctor.getHospital());
            preparedStatement.setString(9, doctor.getDepartment());
            preparedStatement.setString(10, doctor.getSpecialisation());
            preparedStatement.setString(11, doctor.getExperience());
            preparedStatement.setString(12, doctor.getPhone());
            preparedStatement.setString(13, doctor.getUsername());
            preparedStatement.setString(14, doctor.getPassword());
            System.out.println(preparedStatement);
           
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
           
            printSQLException(e);
        }
        return result;
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
