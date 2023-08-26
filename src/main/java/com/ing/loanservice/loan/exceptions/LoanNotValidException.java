package com.ing.loanservice.loan.exceptions;

import java.math.BigDecimal;

public class LoanNotValidException  extends RuntimeException{
    final String   ERROR_MESSAGE="Amount must be between ";
    LoanNotValidException(BigDecimal amount) {
        super(amount+" value is not Acceptable");
    }
}
