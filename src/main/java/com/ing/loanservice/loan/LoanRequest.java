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


    public LoanRequest(BigDecimal amount, Integer customerId, String customerFullName) {
        this.amount = amount;
        this.customerId = customerId;
        this.customerFullName = customerFullName;
    }

    @Override
    public String toString() {
        return "LoanRequest{" +
                "amount=" + amount +
                ", customerId=" + customerId +
                ", customerFullName='" + customerFullName + '\'' +
                '}';
    }
}
