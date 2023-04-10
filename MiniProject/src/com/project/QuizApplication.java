package com.project;


import java.sql.SQLException;
import java.util.Scanner;

public class QuizApplication {

	public static void main(String[] args) throws SQLException {
		int mark=0;
		int option=0;
		do
		{
		Question question=new Question();
		Scanner scanner=new Scanner(System.in);
		Student student=new Student();
		Result result=new Result();
		Admin admin=new Admin();
		System.out.println();
		System.out.println("===== Welcome to Quiz Based Application ======");
		System.out.println();
		System.out.println("============= User Operation ===============");
		System.out.println();
		System.out.println("1. Student Registration");
		System.out.println("2. Student login");
		System.out.println("3. Display the list of questions");
		System.out.println("4. Store Quiz result into database");
		System.out.println("5. Display Quiz Result");
		System.out.println();
		System.out.println("=========== Admin Operation ================");
		System.out.println();
		System.out.println("6. Admin Registration");
		System.out.println("7. Display all Student score as per ascending order");
		System.out.println("8. Fetch student score by using id");
		System.out.println("9. Add questions with 4 options into database");
		System.out.println();
		System.out.println("============= Enter 10 To Exit ===============");
		System.out.println();
		System.out.println("Enter Your Choice");
		
		option=scanner.nextInt();
	
		switch(option)
		{
		
		case 1:
			student.studentRegistration();
			break;
			
		case 2:
			student.studentLogin();
			break;
			
		case 3:
			
			mark=question.displayAllQuestions();
			break;
			
		case 4:
			
			System.out.println("Result Already Stored Successfully");
			break;
		case 5:
			result.displayQuizResult();
			break;
			
		case 6:
			admin.adminRegistration();
			break;
			
		case 7:
			result.displayAllStudentsScore();
			break;
			
		case 8:
			System.out.println("Enter Student id");
			int id=0;
			try
			{
				id=scanner.nextInt();
			}
			catch (Exception e) {
				e.getMessage();
			}
			result.getStudeScoreById(id);
			break;
			
			
		case 9:
			admin.addQuestionWithAnswer();
			break;
		
		default:
				System.out.println("=========== Application Stopped ============");
				
			scanner.close();
		}
		
	}
		while(option!=10);
	}
}
