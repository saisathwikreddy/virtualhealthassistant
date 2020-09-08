package com.virtualHealthAssistant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.virtualHealthAssistant.model.patientregister;

public class patientregisterDao {
	public int registerPatient(patientregister patient) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO patienttable" +
            " (email, firstname, lastname, dob, age, sex, username, password, phonenumber, bloodgroup) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
   
        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        
        
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtualhealthassitant","root","vssroma@123");

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, patient.getEmailid());
            preparedStatement.setString(2, patient.getFirstName());
            preparedStatement.setString(3, patient.getLastName());
            preparedStatement.setString(4, patient.getDOB());
            preparedStatement.setString(5, patient.getAge());
            preparedStatement.setString(6, patient.getSex());
            preparedStatement.setString(7, patient.getUsername());
            preparedStatement.setString(8, patient.getPassword());
            preparedStatement.setString(9, patient.getPhone());
            preparedStatement.setString(10,patient.getBloodgroup());
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
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
