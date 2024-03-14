package com.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AdviceExceptionHandler{
        @ExceptionHandler({BusinessException.class})
        @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
        public ErrorResponse handlePrecondition(final Exception e, final WebRequest request){
            return ErrorResponse.builder().message(e.getLocalizedMessage()).businessException(true).build();
        }

        @ExceptionHandler({NotFoundException.class})
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ErrorResponse handleNotFound(final Exception e, final WebRequest request){
            return ErrorResponse.builder().message(e.getLocalizedMessage()).notFoundException(true).build();
        }

}
