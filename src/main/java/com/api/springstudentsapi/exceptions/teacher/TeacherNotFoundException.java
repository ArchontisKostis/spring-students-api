package com.api.springstudentsapi.exceptions.teacher;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(String msg) {
        super(msg);
    }
}
