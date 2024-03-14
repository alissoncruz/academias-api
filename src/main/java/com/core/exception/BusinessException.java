package com.core.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

@ResponseStatus(PRECONDITION_FAILED)
public class BusinessException extends RuntimeException{

    public BusinessException(String exception){
        super(exception);
    }
}
