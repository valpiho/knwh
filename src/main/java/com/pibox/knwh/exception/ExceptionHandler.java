package com.pibox.knwh.exception;

import com.pibox.knwh.exception.domain.UniqueFieldExistException;
import com.pibox.knwh.exception.domain.UniqueFieldExistExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    public final ResponseEntity<Object> handleVatNumberException(UniqueFieldExistException exception) {
        UniqueFieldExistExceptionResponse exceptionResponse = new UniqueFieldExistExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
