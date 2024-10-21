package io.reactivestax.utils;

import io.reactivestax.entity.Courses;
import io.reactivestax.entity.Enrollments;
import io.reactivestax.entity.Grades;
import io.reactivestax.entity.Students;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

   private static SessionFactory sessionFactory;

   static {
       try {
           sessionFactory = new Configuration()
                   .configure("hibernate.cfg.xml")
                   .addAnnotatedClass(Grades.class)
                   .addAnnotatedClass(Courses.class)
                   .addAnnotatedClass(Enrollments.class)
                   .addAnnotatedClass(Students.class)
               .buildSessionFactory();
       } catch (Throwable ex) {
           throw new ExceptionInInitializerError(ex);
       }
   }

   public static SessionFactory getSessionFactory() {
       return sessionFactory;
   }
}
