package com.ing.loanservice.loan.utils;

import com.ing.loanservice.loan.exceptions.CustomerIsNotValidException;
import com.ing.loanservice.loan.exceptions.LoanNotValidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.MethodNotAllowedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RestExceptionHandlerTest {

    private RestExceptionHandler restExceptionHandler;

    @Mock
    private LoanNotValidException loanNotValidException;

    @Mock
    private CustomerIsNotValidException customerIsNotValidException;

    @Mock
    private MethodNotAllowedException methodNotAllowedException;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        restExceptionHandler = new RestExceptionHandler();
    }

    @Test
    void testLoanNotValidException() {
        when(loanNotValidException.getMessage()).thenReturn("Loan validation failed");
        ResponseEntity<Object> response = restExceptionHandler.loanIsNotValid(loanNotValidException);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    void testCustomerIsNotValidException() {
        when(customerIsNotValidException.getMessage()).thenReturn("Customer not found");
        ResponseEntity<Object> response = restExceptionHandler.customerIsNotValidException(customerIsNotValidException);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    void testMethodNotAllowedException() {
        when(methodNotAllowedException.getMessage()).thenReturn("HTTP method not supported");
        ResponseEntity<Object> response = restExceptionHandler.handlingOtherHttpMethods(methodNotAllowedException);
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    }
}