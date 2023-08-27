package com.ing.loanservice.loan;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;


@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

    @Query( "SELECT  sum(loan.amount ) FROM Loan loan where loan.customerId =:customerId ")
    BigDecimal getCustomerLoanSumByCustomerId(Long customerId);

}
