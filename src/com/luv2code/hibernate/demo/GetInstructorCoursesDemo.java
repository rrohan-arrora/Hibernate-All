package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class GetInstructorCoursesDemo {
		
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml") // there is no need to mention the file name, it works that way as well
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			
			//start a transaction
			session.beginTransaction();
			
			// get the instructor courses
			int theId = 1;
			Instructor theInstructor = session.get(Instructor.class, theId);
			
			System.out.println(theInstructor.getCourses());
			
			//commit transaction
			session.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			
			factory.close();
		}
	}
}
