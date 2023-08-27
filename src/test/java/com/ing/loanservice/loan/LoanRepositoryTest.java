package com.ing.loanservice.loan;

import jakarta.persistence.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@PropertySource(
        "application-test.properties")
@DataJpaTest(properties =
        "spring.jpa.properties.javax.persistence.validation.mode=none")
@Table(name = "loan")
public class LoanRepositoryTest {

    @Autowired
    private LoanRepository loanRepository;

    @BeforeEach
    public void setUp() {
        Loan loan1 = new Loan(new BigDecimal("1000"), 1, "John Doe", "ref123", LocalDateTime.now());
        Loan loan2 = new Loan(new BigDecimal("2000"), 1, "John Doe", "ref124", LocalDateTime.now());
        Loan loan3 = new Loan(new BigDecimal("3000.25"), 1, "John Doe", "ref125", LocalDateTime.now());

        loanRepository.saveAll(List.of(loan1, loan2, loan3));
    }

    @Test
    public void It_Should_Get_A_CustomerId_And_Return_Customer_Loan_Sum() {

        //Given
            Long customerId = 1L;
            BigDecimal expectedSum = new BigDecimal("6000.25");

        //When
            BigDecimal result = loanRepository.getCustomerLoanSumByCustomerId(customerId);

        //Then
            assertThat(result).isEqualTo(expectedSum);



    }
}
