package com.linnea.Inlamning1BackEnd.exception;

public class NotUniqueDateOfBirthException extends RuntimeException {

    public NotUniqueDateOfBirthException() {

        super("Födelsedata måste vara unikt, dubbletter ej tillåtet.");
    }
}
