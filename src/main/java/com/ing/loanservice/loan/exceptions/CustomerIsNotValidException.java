package com.ing.loanservice.loan.exceptions;

import java.math.BigDecimal;

public class CustomerIsNotValidException extends RuntimeException{
    CustomerIsNotValidException(Integer customerId) {
        super(customerId+" is not a valid CustomerId");
    }

}
