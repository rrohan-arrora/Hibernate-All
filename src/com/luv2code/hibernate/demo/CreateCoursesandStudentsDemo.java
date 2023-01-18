package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCoursesandStudentsDemo {
		
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
//			Course theCourse = new Course("A beautiful girl and a blowjon");
			Course theCourse = new Course("White dick liking blonde vagina");
			
			// save the course
			session.save(theCourse);
			
			// create students
			Student theStudent1 = new Student("rohan", "arora", "rohan.arora@gmail.com");
			Student theStudent2 = new Student("sanju", "mehla", "sanju.mehal@gmail.com");
			
			// add students to the course
			theCourse.addStudent(theStudent1);
			
			// add course to students
			theStudent2.addCourse(theCourse);
			
			// save the students
			session.save(theStudent2);
			session.save(theStudent1);
			
			//commit transaction
			session.getTransaction().commit();
		}
		
		finally {
			factory.close();
		}
	}
}
