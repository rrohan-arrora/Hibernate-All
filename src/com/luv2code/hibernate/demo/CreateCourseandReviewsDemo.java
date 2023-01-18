package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class CreateCourseandReviewsDemo {
		
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml") // there is no need to mention the file name, it works that way as well
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			//start a transaction
			session.beginTransaction();
			
			// create a course
			Course course = new Course("How to last longer on bed");
			
			// add some reviews to the course
			course.addReview(new Review("Great practical work shown"));
			course.addReview(new Review("But bed was smaller"));
			course.addReview(new Review("Such a good book"));
			
			// save the course and reviews would be saved automatically
			System.out.println(course);
			session.save(course);
			
			//commit transaction
			session.getTransaction().commit();
		}
		
		finally {
			factory.close();
		}
	}
}
