package io.reactivestax.dao;

import io.reactivestax.entity.Students;
import io.reactivestax.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StudentsDAO {

    public void addStudent(Students students){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.persist(students);
            transaction.commit();
            System.out.println("Student Commited!");
        } catch (Exception e) {
            if(transaction!=null){
                transaction.rollback();
            }
        }
    }
}
