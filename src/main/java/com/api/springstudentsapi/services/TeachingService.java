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

    @Autowired
    public TeachingService(TeachingRepository teachingRepository) {
        this.teachingRepository = teachingRepository;
    }

    public Collection<Teaching> getTeachings() {
        return this.teachingRepository.findAll();
    }

    public Teaching assignTeacherToCourse(Teacher teacher, Course course){
        Teaching teaching = new Teaching(teacher, course);
        return teachingRepository.save(teaching);
    }
}
