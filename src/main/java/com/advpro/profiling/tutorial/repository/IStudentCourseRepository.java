package com.advpro.profiling.tutorial.repository;

import com.advpro.profiling.tutorial.model.StudentCourse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface IStudentCourseRepository extends Repository<StudentCourse, Long> {
    @Query("SELECT DISTINCT s, c FROM Student s INNER JOIN StudentCourse sc ON s.id = sc.student.id INNER JOIN Course c ON sc.course.id = c.id")
    List<Object[]> getAllStudentsWithTheirCoursesOptimize();
}
