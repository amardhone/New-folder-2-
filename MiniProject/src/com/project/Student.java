package com.project;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Student {

	Connections connections=new Connections();
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	Connection connection=null;
	Scanner scanner=new Scanner(System.in);

	
	
	public void registration(String firstName,String lastName,String username,String password,String city, String email,String mobile) throws SQLException
	{
		
		try {
			connection=connections.getConnection();
			preparedStatement=connection.prepareStatement("insert into student(firstName,lastName,userName,password,city,email,mobile) values(?,?,?,?,?,?,?)");
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, username);
			preparedStatement.setString(4, password);
			preparedStatement.setString(5, city);
			preparedStatement.setString(6, email);
			preparedStatement.setString(7, mobile);
			preparedStatement.executeUpdate();
			System.out.println("Registration Completed Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
			preparedStatement.close();
		}
	}


	public boolean checkStudentLogin(String username ,String password) throws SQLException
	{
		connection=connections.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			
			preparedStatement=connection.prepareStatement("select * from student");
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				if((username.equals(resultSet.getObject("userName")))&&(password.equals(resultSet.getObject("password"))))
				{
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			resultSet.close();
			connection.close();
			preparedStatement.close();
		}
		return false;
	}
	
	
	
	public void studentLogin(String username, String password) throws SQLException
	{
	
		if(checkStudentLogin(username, password))
		{
			System.out.println("Login Successfull");	
		}

		else
		{
			System.out.println("invalid username or password");
		}
	}
	
	
	
	public void studentRegistration() throws SQLException
	{
		System.out.println("enter first name");
		String firstName=scanner.nextLine();
		scanner.nextLine();
		System.out.println("enter last name");
		String lastName=scanner.nextLine();
		System.out.println("enter username");
		String username=scanner.nextLine();
		System.out.println("enter password");
		String pass=scanner.nextLine();
		System.out.println("ENter city");
		String city=scanner.nextLine();
		System.out.println("enter email id");
		String email=scanner.nextLine();
		System.out.println("enter mobile number");
		String mobile=scanner.nextLine();
		registration(firstName, lastName, username, pass, city, email, mobile);
	}
	
	
	
	public void studentLogin() throws SQLException
	{
		System.out.println("enter username");
		String username=scanner.next();
		System.out.println("enter password");
		String pass=scanner.next();
		studentLogin(username, pass);
	}
}
