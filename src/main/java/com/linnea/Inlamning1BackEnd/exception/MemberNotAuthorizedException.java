package com.linnea.Inlamning1BackEnd.exception;

public class MemberNotAuthorizedException extends RuntimeException {

    public MemberNotAuthorizedException() {

        super("Ej tillåtet att uppdatera andra medlemmars uppgifter.");
    }
}
