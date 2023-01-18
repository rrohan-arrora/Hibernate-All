package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {
		
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
			// create the objects
			Instructor theInstructor = 
					new Instructor("rohan", "arora", "rohan.arora@gmail.com", null);
			InstructorDetail theDetail = new InstructorDetail("www.google.com", "love 2 learn!!");
			
			Instructor theInstructor1 = 
					new Instructor("Sanju", "mehla", "sanju.mehla@gmail.com", null);
			InstructorDetail theDetail1 = new InstructorDetail("www.sanjumehla.com", "thinks about girls all time!!");
			
			//associate the objects
			theInstructor.setInstructorDetail(theDetail);
			theInstructor1.setInstructorDetail(theDetail1);
			
			//start a transaction
			session.beginTransaction();
			
			//save the object
			session.save(theInstructor);
			session.save(theInstructor1); // this will also save the details object due to cascade all				
			
			//commit transaction
			session.getTransaction().commit();
		}
		
		finally {
			factory.close();
		}
	}
}
