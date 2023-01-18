package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemoLazyLoading {
		
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
			int theId=1;
			
			//start a transaction
			session.beginTransaction();
			
			// option2: Hibernate query with HQL instead of session
			Query<Instructor> query =
					session.createQuery("from Instructor i "
							+ "JOIN FETCH i.courses "
							+ "where i.id= :theId", Instructor.class);
			
			query.setParameter("theId", theId);
			Instructor theInstructor = query.getSingleResult();
			
			System.out.println("The instructor for the course is: "+ theInstructor);
						
			//commit transaction
			session.getTransaction().commit();
			factory.close();
			
			System.out.println("Courses related to the instructor: "+ theInstructor.getCourses());
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			
			factory.close();
		}
	}
}
