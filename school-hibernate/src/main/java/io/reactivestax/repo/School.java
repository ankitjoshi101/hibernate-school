package io.reactivestax.repo;


import io.reactivestax.entity.Courses;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.NoSuchElementException;

public class School {

    private School(){

    }
    public static Courses getCourseWithName(String courseName, Session session) {

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Courses> coursesCriteriaQuery = criteriaBuilder.createQuery(Courses.class);
        Root<Courses> root = coursesCriteriaQuery.from(Courses.class);
        coursesCriteriaQuery.select(root).where(criteriaBuilder.equal(root.get("courseName"), courseName));
        return session.createQuery(coursesCriteriaQuery).getSingleResult();
    }


    public static void rollbackTransaction(Transaction transaction) {
        if (transaction != null && transaction.getStatus() == TransactionStatus.ACTIVE) {
            transaction.rollback();
        }
    }

    public static Courses getCourses(String courseName, Session session) {
        Courses course = School.getCourseWithName(courseName, session);

        if(course ==null){
            throw new NoSuchElementException("Course Does Not Exist");
        }
        return course;
    }

}
