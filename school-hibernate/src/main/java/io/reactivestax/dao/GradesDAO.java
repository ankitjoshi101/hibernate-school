package io.reactivestax.dao;

import io.reactivestax.entity.Courses;
import io.reactivestax.repo.School;
import io.reactivestax.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GradesDAO {

    public void assignGrade(int studentId, String courseName, double grade) {

        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Courses courses = School.getCourses(courseName, session);

        }

    }
}
