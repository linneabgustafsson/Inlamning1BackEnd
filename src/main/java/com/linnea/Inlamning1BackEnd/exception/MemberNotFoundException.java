package com.linnea.Inlamning1BackEnd.exception;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(Long id) {

        super("Medlem med id-nummer " + id + " hittades inte.");
    }
}
