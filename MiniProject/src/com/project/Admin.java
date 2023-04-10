package com.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin {

	Result result=new Result();
	Connections connections=new Connections();
	Scanner scanner=new Scanner(System.in);
	Question question=new Question();
	
	
	public void adminRegistration() throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		connection=connections.getConnection();
		try {
			System.out.println("Enter first name");
			String firstName=scanner.next();
			System.out.println("Enter last name");
			String lastName=scanner.next();
			System.out.println("Enter username");
			String username=scanner.next();
			System.out.println("Enter password");
			String password=scanner.next();
			System.out.println("Enter city");
			String city=scanner.next();
			System.out.println("Enter email id");
			String email=scanner.next();
			System.out.println("Enter mobile number");
			String mobile=scanner.next();
			
			preparedStatement=connection.prepareStatement("insert into admin(firstName,lastName,username,password,city,email,mobile)values(?,?,?,?,?,?,?)");
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, username);
			preparedStatement.setString(4, password);
			preparedStatement.setString(5, city);
			preparedStatement.setString(6, email);
			preparedStatement.setString(7, mobile);
			
			preparedStatement.executeUpdate();
			System.out.println("Admin Registered Successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
			preparedStatement.close();
			}	
		}
	
	public boolean checkAdminLogin(String username,String password) throws SQLException
	{
		
		Connection connection=null;
		ResultSet resultSet=null;
		PreparedStatement preparedStatement=null;
		
		connection=connections.getConnection();
		try {
			preparedStatement=connection.prepareStatement("select * from admin");
			resultSet=preparedStatement.executeQuery();
		
			while(resultSet.next())
			{
				if((username.equals(resultSet.getObject(4)))&&(password.equals(resultSet.getObject(5))))
				{
					return true;
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			connection.close();
			preparedStatement.close();
			resultSet.close();
		}
		return false;
	}
	
	
	public void addQuestionWithAnswer() throws SQLException
	{
	System.out.println("Enter Admin Username");
	String username=scanner.next();
	System.out.println("Enter Admin Password");
	String pass=scanner.next();
	
	if(checkAdminLogin(username, pass))
	{
		scanner.nextLine();
		System.out.println("Enter Qestion");
		String questions=scanner.nextLine();
		System.out.println("Enter Option 1");
		String option1=scanner.nextLine();
		System.out.println("Entrer Option 2");
		String option2=scanner.nextLine();
		System.out.println("Enter Option 3");
		String option3=scanner.nextLine();
		System.out.println("Enter Option 4");
		String option4=scanner.nextLine();
		System.out.println("Enter Correct Answer");
		String answer=scanner.nextLine();
		question.addQuestion(questions, option1, option2, option3, option4, answer);
	}
	}
}
