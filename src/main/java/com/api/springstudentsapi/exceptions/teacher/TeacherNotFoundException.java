package com.api.springstudentsapi.exceptions.teacher;

import com.api.springstudentsapi.exceptions.NotFoundException;

public class TeacherNotFoundException extends NotFoundException {
    public TeacherNotFoundException(String msg) {
        super(msg);
    }
}
