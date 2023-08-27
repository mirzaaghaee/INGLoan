package com.ing.loanservice.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan registerNewLoan(LoanRequest loanRequest) {
        Loan loan=createLoanFromRequest(loanRequest);
        return loanRepository.save(loan);
    }
    public BigDecimal getSumOfCustomerLoans(Long customerId) {
        return loanRepository.getCustomerLoanSumByCustomerId(customerId);
    }


    private Loan createLoanFromRequest(LoanRequest loanRequest) {
        return new Loan(
                loanRequest.getAmount(),
                loanRequest.getCustomerId(),
                loanRequest.getCustomerFullName(),
                generateReferenceNo(),
                LocalDateTime.now()
        );
    }

    private static String generateReferenceNo() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = now.format(formatter);
        int randomNum = new Random().nextInt(10000);
        String referenceNo = String.format("%s%04d", formattedDateTime, randomNum);
        referenceNo = String.format("%-16s", referenceNo).replace(' ', '0');
        return referenceNo;
    }

}
