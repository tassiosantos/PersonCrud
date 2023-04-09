package com.api.personcrud.controllers;



import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@RestControllerAdvice
public class ControladorExcessoes extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        ResponseEntity<Object> responseEntity = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Tem alguma coisa faltando ai!");
        return responseEntity;
    }

  
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
                
            ResponseEntity<Object> responseEntity = ResponseEntity.status(status).body(ex.getMessage() + " handleHttpRequestMethodNotSupported");
        return responseEntity;
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,WebRequest request) {

    ResponseEntity<Object> responseEntity = ResponseEntity.status(status).body(ex.getMessage() + " handleMethodArgumentNotValid");
    
    return responseEntity;
}



    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        ResponseEntity<Object> responseEntity = ResponseEntity.status(status).body("O que está tentando mandar?");
    
        return responseEntity;
    }



    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        ResponseEntity<Object> responseEntity = ResponseEntity.status(status).body(ex.getMessage() + " handleMissingServletRequestParameter");
    
        return responseEntity;
    }


    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        ResponseEntity<Object> responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Esta tentando ir para onde?");
        
    return responseEntity;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ResponseEntity<Object> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Está tentando fazer o que?");
        
    return responseEntity;
    }

}


