package com.linnea.Inlamning1BackEnd.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

@RestControllerAdvice
public class ApiExceptionHandler {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<?> handleMemberNotFoundException(MemberNotFoundException e,
                                                           HttpServletRequest getPath) {

        LinkedHashMap<String, Object> errorResponse404NotFound = new LinkedHashMap<>();
        errorResponse404NotFound.put("timestamp", LocalDateTime.now().format(dateTimeFormatter));
        errorResponse404NotFound.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse404NotFound.put("error", "Not Found");
        errorResponse404NotFound.put("message", e.getMessage());
        errorResponse404NotFound.put("path", getPath.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse404NotFound);
    }

    @ExceptionHandler(NotUniqueDateOfBirthException.class)
    public ResponseEntity<?> handleNotUniqueDateOfBirthException(NotUniqueDateOfBirthException e,
                                                                 HttpServletRequest getPath) {

        LinkedHashMap<String, Object> errorResponse400BadRequest = new LinkedHashMap<>();
        errorResponse400BadRequest.put("timestamp", LocalDateTime.now().format(dateTimeFormatter));
        errorResponse400BadRequest.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse400BadRequest.put("error", "Bad Request");
        errorResponse400BadRequest.put("message", e.getMessage());
        errorResponse400BadRequest.put("path", getPath.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse400BadRequest);
    }

    @ExceptionHandler(MemberNotAuthorizedException.class)
    public ResponseEntity<?> handleFelMedlem(MemberNotAuthorizedException e, HttpServletRequest getPath) {
        LinkedHashMap<String, Object> errorResponse403Forbidden = new LinkedHashMap<>();
        errorResponse403Forbidden.put("timestamp", LocalDateTime.now().format(dateTimeFormatter));
        errorResponse403Forbidden.put("status", HttpStatus.FORBIDDEN.value());
        errorResponse403Forbidden.put("error", "Bad Request");
        errorResponse403Forbidden.put("message", e.getMessage());
        errorResponse403Forbidden.put("path", getPath.getRequestURI());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse403Forbidden);
    }

    //NAMN PÅ METODEN
    //Kod 400 Bad Request - skrivit in text i dateOfBirth
    //Fel innan din controller ens börjar köra - requesten kommer aldrig fram till metoden
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleExceptionBadRequest(HttpServletRequest requestSomHamtarPath){

        //Vad ska stå i meddelandet?
        LinkedHashMap<String, Object> errorResponse400BadRequest = new LinkedHashMap<>();
        errorResponse400BadRequest.put("timestamp", LocalDateTime.now().format(dateTimeFormatter));
        errorResponse400BadRequest.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse400BadRequest.put("error", "Bad request");
        errorResponse400BadRequest.put("message", "Du har skrivit in ett otillåtet eller felaktigt värde");
        errorResponse400BadRequest.put("path", requestSomHamtarPath.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse400BadRequest);
    }

    //Kod 400 Bad Request - lämnat ett tomt fält el. för lång input
    //Det är fel från @Valid
    //Här lyckas Spring först läsa in JSON till DTO:n. Men sedan körs valideringen.
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleExceptionBadRequest2(HttpServletRequest requestSomHamtarPath)   {

        //Vad ska stå i meddelandet?
        LinkedHashMap<String, Object> errorResponse400BadRequest = new LinkedHashMap<>();
        errorResponse400BadRequest.put("timestamp", LocalDateTime.now().format(dateTimeFormatter));
        errorResponse400BadRequest.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse400BadRequest.put("error", "Bad request");
        errorResponse400BadRequest.put("message", "Ett fält som inte får vara tomt är tomt," +
                " text i något fält överstiger tillåtet antal tecken," +
                " NÅGOT MER?");
        errorResponse400BadRequest.put("path", requestSomHamtarPath.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse400BadRequest);
    }
}
