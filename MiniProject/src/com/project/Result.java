package com.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Result {

	Connections connections=new Connections();
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	Student student=new Student();
	Scanner scanner=new Scanner(System.in);
	
	
	public void displayScoreUsingUsername(String username,String password) throws SQLException 
	{
		int score=0;
		connection=connections.getConnection();
			try {
				if(student.checkStudentLogin(username, password))
				{
					preparedStatement=connection.prepareStatement("select score from student where username=?");
					preparedStatement.setString(1, username);
					resultSet=preparedStatement.executeQuery();
					while(resultSet.next())
					{
						score=resultSet.getInt("score");
					}
					System.out.println("Score is "+score);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally
			{
				connection.close();
				resultSet.close();
				preparedStatement.close();
			}
		
	}
	
	
	
	public void displayAllStudentsScore() throws SQLException
	{
		Admin admin=new Admin();
		System.out.println("Enter Admin Username");
		String username=scanner.next();
		System.out.println("Enter Admin Password");
		String password=scanner.next();
		if(admin.checkAdminLogin(username, password))
		{
		connection=connections.getConnection();
		try {
			preparedStatement=connection.prepareStatement("select firstName,lastName,score from student  ORDER BY score ASC");
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				System.out.println();
				System.out.println("First Name is :"+resultSet.getString("firstName"));
				System.out.println("Last Name is :"+resultSet.getString("lastName"));
				System.out.println("Score is :"+resultSet.getInt("score"));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
			preparedStatement.close();
			resultSet.close();
		}
		
		}
		else
		{
			System.out.println("invalid username or password");
		}
	}
	
	
	
	public void getStudeScoreById(int id) throws SQLException
	{
		Admin admin=new Admin();
		System.out.println("Enter Admin Username");
		String username=scanner.next();
		System.out.println("Enter Admin Password");
		String password=scanner.next();
		if(admin.checkAdminLogin(username, password))
		{
		connection=connections.getConnection();
		int score=0;
		try {
			preparedStatement=connection.prepareStatement("select score from student where sid=?");
			preparedStatement.setInt(1, id);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				score=resultSet.getInt("score");
			}
			System.out.println("Student Id is : "+id);
			System.out.println("Score is "+score);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
			preparedStatement.close();
			resultSet.close();
		}
		}
		else
		{
			System.out.println("invalid username or password");
		}
		
	}
	
	public String getGrade()
	{
		
		
		return "grade";
	}
	
	
	
	
	public void displayQuizResult() throws SQLException
	{
		System.out.println("Enter Username");
		String username=scanner.next();
		System.out.println("Enter Password");
		String pass=scanner.next();
		displayScoreUsingUsername(username, pass);
	}
}
