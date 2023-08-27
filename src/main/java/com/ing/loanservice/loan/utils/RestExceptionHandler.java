package com.ing.loanservice.loan.utils;

import com.ing.loanservice.loan.exceptions.CustomerIsNotValidException;
import com.ing.loanservice.loan.exceptions.LoanNotValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);
    private final String LOAN_NOT_ACCEPTED="Loan is not accepted";
    private final String CUSTOMER_NOT_VALID="Customer is Not found";
    private final String METHOD_NOT_SUPPORTED="Http method is not suppurted";
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
    private void logError(String message){
        log.error("Advice Logger: %s:%s".formatted(LocalDateTime.now(), message));
    }

    @ResponseBody
    @ExceptionHandler(LoanNotValidException.class)
    ResponseEntity<Object> loanIsNotValid(LoanNotValidException ex) {
        logError(ex.getMessage());
        ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE,LOAN_NOT_ACCEPTED,ex);
        return buildResponseEntity(apiError);
    }



    @ResponseBody
    @ExceptionHandler(CustomerIsNotValidException.class)
    ResponseEntity<Object> customerIsNotValidException(CustomerIsNotValidException ex){
        logError(ex.getMessage());
        ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE,CUSTOMER_NOT_VALID,ex);
        return buildResponseEntity(apiError);
    }
    @ResponseBody
    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    ResponseEntity<Object> handlingOtherHttpMethods(MethodNotAllowedException ex){
        logError(ex.getMessage());
        ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED,METHOD_NOT_SUPPORTED,ex);
        return buildResponseEntity(apiError);

    }




}
