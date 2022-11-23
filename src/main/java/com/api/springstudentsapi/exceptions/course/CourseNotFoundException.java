package com.api.springstudentsapi.exceptions.course;

import com.api.springstudentsapi.exceptions.NotFoundException;

public class CourseNotFoundException extends NotFoundException {

    public CourseNotFoundException(String msg) {
        super(msg);
    }
}
