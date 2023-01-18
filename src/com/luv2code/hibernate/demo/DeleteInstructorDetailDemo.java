package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {
		
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml") // there is no need to mention the file name, it works that way as well
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			
			//start a transaction
			session.beginTransaction();
			
			// get the instructor detail object
			int theId = 3;
			
			// print the instructor detail
			InstructorDetail instructorDetail = 
					session.get(InstructorDetail.class, theId);
			
			// print the associated instructor
			System.out.println("The instructor is " + instructorDetail.getInstructor());
			
			//Detach the associated Instructor from its instructor detail as cascade delete option is removed
			instructorDetail.getInstructor().setInstructorDetail(null);
			
			// delete the Instructor detail
			session.delete(instructorDetail);
			
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
