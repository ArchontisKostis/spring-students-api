package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.entities.Teacher;
import com.api.springstudentsapi.entities.Teaching;
import com.api.springstudentsapi.repositories.TeachingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TeachingService {
    private final TeachingRepository teachingRepository;
    private final TeacherService teacherService;
    private final CourseService courseService;

    @Autowired
    public TeachingService(TeachingRepository teachingRepository, TeacherService teacherService, CourseService courseService) {
        this.teachingRepository = teachingRepository;
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    public Collection<Teaching> getTeachings() {
        return this.teachingRepository.findAll();
    }

    public Teaching assignTeacherToCourse(Long teacherId, Long courseId){
        Teacher foundTeacher = teacherService.getTeacherById(teacherId);
        Course foundCourse = courseService.getCourseById(courseId);

        Teaching teaching = new Teaching(foundTeacher, foundCourse);
        return teachingRepository.save(teaching);
    }
}
