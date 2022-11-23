package com.api.springstudentsapi.exceptions.student;

import com.api.springstudentsapi.exceptions.NotFoundException;

public class StudentNotFound extends NotFoundException {
    public StudentNotFound(String msg) {
        super(msg);
    }
}
