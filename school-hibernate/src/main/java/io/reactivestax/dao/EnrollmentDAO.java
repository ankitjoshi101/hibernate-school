package io.reactivestax.dao;


import io.reactivestax.entity.Courses;
import io.reactivestax.entity.Enrollments;
import io.reactivestax.entity.Students;
import io.reactivestax.repo.School;
import io.reactivestax.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class EnrollmentDAO {

    Logger log = Logger.getLogger(Enrollments.class.getName());

        public void enrollStudent(Long studentId, String studentName, String courseName){
            Transaction transaction = null;

            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                transaction = session.beginTransaction();

                Courses course = School.getCourses(courseName, session);

                Students student = session.get(Students.class, studentId);
                if(student==null){
                    student = new Students();
                    student.setStudentId(studentId);
                    student.setStudentName(studentName);
                    session.merge(student);
                    log.info("Student '" + studentName + "' (ID: " + studentId + ") added to the Students table.");

                }

                Enrollments enrollment = new Enrollments();
                enrollment.setCourses(course);
                enrollment.setStudent(student);
                session.persist(enrollment);

                transaction.commit();
                log.info("Student '" + studentId + "' enrolled in course '" + courseName + "'.");

            }
        }


}
