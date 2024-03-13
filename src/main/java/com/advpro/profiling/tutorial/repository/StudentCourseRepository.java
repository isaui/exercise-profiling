package com.advpro.profiling.tutorial.repository;

import com.advpro.profiling.tutorial.model.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author muhammad.khadafi
 */
@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    List<StudentCourse> findByStudentId(Long studentId);
    @Query("SELECT DISTINCT s, c FROM Student s INNER JOIN StudentCourse sc ON s.id = sc.student.id INNER JOIN Course c ON sc.course.id = c.id")
    List<Object[]> getAllStudentsWithTheirCoursesOptimize();
}