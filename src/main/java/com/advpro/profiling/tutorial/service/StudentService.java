package com.advpro.profiling.tutorial.service;

import com.advpro.profiling.tutorial.model.Course;
import com.advpro.profiling.tutorial.model.Student;
import com.advpro.profiling.tutorial.model.StudentCourse;
import com.advpro.profiling.tutorial.repository.StudentCourseRepository;
import com.advpro.profiling.tutorial.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author muhammad.khadafi
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public List<StudentCourse> getAllStudentsWithCourses() {
        List<Object[]> studentJoinCourse = studentCourseRepository.getAllStudentsWithTheirCoursesOptimize();
        List<StudentCourse> studentCourses = new ArrayList<>();

        for(int i = 0; i < studentJoinCourse.size(); i++) {
            Student student = (Student) studentJoinCourse.get(i)[0];
            Course course = (Course) studentJoinCourse.get(i)[1];
            StudentCourse studentCourse = new StudentCourse(student, course);
            studentCourses.add(studentCourse);
        }
        return studentCourses;
    }

    public Optional<Student> findStudentWithHighestGpa() {
        Query query = entityManager.createQuery("SELECT s FROM Student s WHERE s.gpa = (SELECT MAX(s2.gpa) FROM Student s2)");
        query.setMaxResults(1);
        return Optional.ofNullable((Student) query.getResultList().stream().findFirst().orElse(null));
    }

    public String joinStudentNames() {
        List<Student> students = studentRepository.findAll();
        String result = "";
        for (Student student : students) {
            result += student.getName() + ", ";
        }
        return result.substring(0, result.length() - 2);
    }
}

