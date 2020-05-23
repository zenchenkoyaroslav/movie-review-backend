package com.yz.moviereview.config;

import com.yz.moviereview.exceptions.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>("AS" , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ ValidationException.class})
    public ResponseEntity<Object> handleException() {
        return new ResponseEntity<Object>("Client error" , HttpStatus.BAD_REQUEST);
    }

}
