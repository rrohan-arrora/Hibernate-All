package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteCoursesandStudentsDemo {
		
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml") // there is no need to mention the file name, it works that way as well
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			//start a transaction
			session.beginTransaction();
			
			// create a course
			int theId=10;
			
			// delete the course
			Course c = session.get(Course.class, theId);
			session.delete(c);
			
			// delete the student
			int theSId = 2;
			Student s = session.get(Student.class, theSId);
			session.delete(s);
			
			//commit transaction
			session.getTransaction().commit();
		}
		
		finally {
			factory.close();
		}
	}
}
