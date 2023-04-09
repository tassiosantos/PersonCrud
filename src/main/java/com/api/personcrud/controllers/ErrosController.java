package com.api.personcrud.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrosController implements ErrorController{
    
    @RequestMapping("/error")
    public ResponseEntity<String> handleError(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        String message = "";
        if (status == HttpStatus.NOT_FOUND) {
            message = "Não tem nada aqui!";
        } else if (status == HttpStatus.BAD_REQUEST) {
            message = "Requisição inválida";
        }else if (status == HttpStatus.INTERNAL_SERVER_ERROR){
            message = "Erro de requisição!";
        }

        return new ResponseEntity<>(message, status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }


}
