package com.project;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Question {

	Connection connection=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	Scanner scanner=new Scanner(System.in);
	Connections connections=new Connections();
	
	private String grades="";
	private int scores;
	private double totalScores=0;
	
	

public Question()
{
	
}
	

	public Question(String grades, int scores, int totalScores) {
		super();
		this.grades = grades;
		this.scores = scores;
		this.totalScores = totalScores;
	}

	public void addQuestion(String question,String option1,String option2,String option3,String option4,String answer) throws SQLException
	{
		
		
		try {
			connection=connections.getConnection();
			preparedStatement=connection.prepareStatement("insert into Question(question,option1,option2,option3,option4,answer)values(?,?,?,?,?,?)");
			preparedStatement.setString(1, question);
			preparedStatement.setString(2, option1);
			preparedStatement.setString(3, option2);
			preparedStatement.setString(4, option3);
			preparedStatement.setString(5, option4);
			preparedStatement.setString(6, answer);
			int a=preparedStatement.executeUpdate();
			System.out.println("Qeustions Added Successfully "+a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
			preparedStatement.close();
		}
		
		
		
	}
	
	public void displayQuestion(String username) throws SQLException
	{
		int i=1;
		
	
			try {
				
				connection=connections.getConnection();
				preparedStatement=connection.prepareStatement("select * from Question");
				resultSet=preparedStatement.executeQuery();
				
		
				while(resultSet.next())
				{
					String question=resultSet.getString("question");
					String option1=resultSet.getString("option1");
					String option2=resultSet.getString("option2");
					String option3=resultSet.getString("option3");
					String option4=resultSet.getString("option4");
					System.out.println();
					System.out.println(i+") "+question);
					System.out.println("a. "+option1);
					System.out.println("b. "+option2);
					System.out.println("c. "+option3);
					System.out.println("d. "+option4);
					System.out.println();
					System.out.println("Enter Your Answer");
					String ans=scanner.next();
					if(ans.equalsIgnoreCase(resultSet.getString("answer")))
					{
						System.out.println("Correct Answer");
						scores++;
						totalScores++;
					}
					else
					{
						System.out.println("Wrong Answer");
						totalScores++;
					}
				
					i++;	
			}
				System.out.println("Score is "+scores);
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			finally {
				connection.close();
				resultSet.close();
				preparedStatement.close();
			}
			System.out.println("My name is ma");
			System.out.println("total score is "+totalScores);
			
			
			double gr=(scores/totalScores)*100;
			String grade="";
			if(gr>90)
			{
				grades="A+";
			}
			else if(gr>80)
			{
				grades="A";
			}
			else if(gr>70)
			{
				grades="B+";
				
			}
			else if(gr>60)
			{
				grades="C+";
			}
			else if(gr>50)
			{
				grades="C";
			}
			else
			{
				grades="Fail";
				
			}
			
			System.out.println("Grade is "+grades);
			updateMarksAndGrade(username,scores,grades);
			
		}	
	
	public void storeResult(int mark) throws SQLException
	{
		Student student=new Student();
		System.out.println("Enter Student Username");
		String username =scanner.next();
		System.out.println("Enter Password");
		String password=scanner.next();
		student.checkStudentLogin(username, password);
		connection=connections.getConnection();
		try {
			preparedStatement=connection.prepareStatement("update student set score=? , grade=? where userName=?");
			preparedStatement.setInt(1, mark);
			preparedStatement.setString(2, grades);
			preparedStatement.setString(3, username);
			preparedStatement.executeUpdate();			
			} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public int displayAllQuestions() throws SQLException
	{
		int a=0;
		Student student=new Student();
		System.out.println("enter username");
		String username=scanner.next();
		System.out.println("enter password");
		String pass=scanner.next();
		if(student.checkStudentLogin(username, pass))
		{
			System.out.println("Login Successfully");
			displayQuestion(username);
		}
		
		return a;
	}
	
	public void updateMarksAndGrade(String username,int scores,String grades) throws SQLException
	{
		try {
			connection=connections.getConnection();
			preparedStatement=connection.prepareStatement("update student set score=?,grade=? where username=?");
			preparedStatement.setInt(1, scores);
			preparedStatement.setString(2, grades);
			preparedStatement.setString(3, username);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			preparedStatement.close();
			connection.close();
			
		}
	}
}
