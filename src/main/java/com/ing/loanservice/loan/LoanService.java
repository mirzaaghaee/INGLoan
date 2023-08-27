package com.ing.loanservice.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Loan loan=getLoanFromDto(loanRequest);
        return loanRepository.save(loan);
    }

    private Loan getLoanFromDto(LoanRequest loanRequest) {
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

    public Long getSumOfloansByCustomerId(Long customerId) {
        return 1L;

    }
}
