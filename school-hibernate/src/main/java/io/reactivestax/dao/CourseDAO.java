package io.reactivestax.dao;

import io.reactivestax.entity.Courses;

import io.reactivestax.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CourseDAO {

    public void addCourse(Courses courses){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.persist(courses);
            transaction.commit();
            System.out.println("[INFO]: New Course Added!");
        }catch (Exception e){
            rollbackTransaction(transaction);
        }
    }

    public List<Courses> listCourses() {
        Transaction transaction = null;
        List<Courses> courses = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Courses> query = session.createQuery("FROM Courses", Courses.class);
            courses = query.getResultList();
            System.out.println("Here");
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return courses;
    }


    private static void rollbackTransaction(Transaction transaction) {
        if(transaction !=null){
            transaction.rollback();
        }
    }

    public void printAllCourses() {
        List<Courses> coursesList = listCourses();

        if (coursesList != null && !coursesList.isEmpty()) {
            System.out.println("List of Courses:");
            for (Courses course : coursesList) {
                System.out.println("ID: " + course.getId() + ", Course Name: " + course.getCourseName());
            }
        } else {
            System.out.println("No courses found.");
        }
    }
}


