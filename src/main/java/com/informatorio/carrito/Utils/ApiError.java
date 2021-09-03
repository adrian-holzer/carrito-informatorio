package com.informatorio.carrito.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class ApiError {


    private Map<String, String> errores;


    public  ApiError(Errors errors){


       this.errores=   handleMethodArgumentNotValid(errors);

    }

    public Map<String, String> handleMethodArgumentNotValid(Errors ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }


    public Map<String, String> getErrores() {
        return errores;
    }
}
