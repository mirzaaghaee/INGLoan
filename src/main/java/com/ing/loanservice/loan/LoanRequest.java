package com.ing.loanservice.loan;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LoanRequest {

    private BigDecimal amount;
    private Integer customerId;
    private String customerFullName;
    private String referenceNo;

    // Getters and setters


    @Override
    public String toString() {
        return "LoanDTO{" +
                "amount=" + amount +
                ", customerId=" + customerId +
                ", customerFullName='" + customerFullName + '\'' +
                ", referenceNo='" + referenceNo + '\'' +
                '}';
    }
}
