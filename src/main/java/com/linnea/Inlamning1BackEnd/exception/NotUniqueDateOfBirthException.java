package com.linnea.Inlamning1BackEnd.exception;

public class NotUniqueDateOfBirthException extends RuntimeException {

    public NotUniqueDateOfBirthException() {

        super("Date of birth måste vara unikt.");
    }
}
