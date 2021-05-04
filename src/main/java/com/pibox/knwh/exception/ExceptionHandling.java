package com.pibox.knwh.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.pibox.knwh.entity.HttpResponse;
import com.pibox.knwh.exception.domain.BadRequestException;
import com.pibox.knwh.exception.domain.UniqueFieldExistException;
import com.pibox.knwh.exception.domain.UniqueFieldExistExceptionResponse;
import com.pibox.knwh.exception.domain.UserNotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandling implements ErrorController {

    public final ResponseEntity<Object> handleVatNumberException(UniqueFieldExistException exception) {
        UniqueFieldExistExceptionResponse exceptionResponse = new UniqueFieldExistExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException exception) {
        return createHttpResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<HttpResponse> badRequestException(BadRequestException exception) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<HttpResponse> validationException(ConstraintViolationException exception) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(UniqueFieldExistException.class)
    public ResponseEntity<HttpResponse> uniqueFieldExistException(UniqueFieldExistException exception) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<HttpResponse> httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                new HttpResponse(httpStatus.value(), httpStatus, message), httpStatus);
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
