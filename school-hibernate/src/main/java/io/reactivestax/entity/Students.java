package io.reactivestax.entity;

import io.reactivestax.school.Course;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "students")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long studentId;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Enrollments> enrollments;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL)
    private Set<Courses> courses;
}
