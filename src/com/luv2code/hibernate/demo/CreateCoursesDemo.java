package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {
		
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
			
			// get the instructor from db
			int theId = 1;
			Instructor theInstructor = session.get(Instructor.class, theId);
			
			// create some courses
			Course theCourse1 = new Course("Random Shit");
			Course theCourse2 = new Course("Make the Vagina water");
			
			// add courses to instructor
			theCourse1.setInstructor(theInstructor);
			theCourse2.setInstructor(theInstructor);
			
			// save the courses
			session.save(theCourse2);
			session.save(theCourse1);
			
			//commit transaction
			session.getTransaction().commit();
		}
		
		finally {
			factory.close();
		}
	}
}
