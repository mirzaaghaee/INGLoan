package com.ing.loanservice.loan;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoanRequest {
    private final Loan loan;
    public LoanRequest(@JsonProperty("customer")Loan loan){
        this.loan=loan;
    }
    public Loan getLoan(){return this. loan;}

    @Override
    public String toString() {
        return "LoanRequest{" +
                "loan=" + loan +
                '}';
    }
}
