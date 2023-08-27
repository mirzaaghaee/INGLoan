package com.ing.loanservice.loan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    private LoanService loanService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        loanService = new LoanService(loanRepository);
    }

    @Test
    public void It_Should_Receive_A_Loan_Request_And_Then_Register_It() {
        // Given
        LoanRequest loanRequest = new LoanRequest(BigDecimal.valueOf(5000), 1, "John Doe");

        // When
        Loan loan = loanService.registerNewLoan(loanRequest);

        // Then
        verify(loanRepository).save(any(Loan.class));
    }

    @Test
    public void It_Shuold_Get_A_CustomerId_And_Then_Return_Sum_Of_Their_Loans() {
        // Given
        Long customerId = 1L;
        BigDecimal expectedSum = BigDecimal.valueOf(10000);

        when(loanRepository.getCustomerLoanSumByCustomerId(customerId)).thenReturn(expectedSum);

        // When
        BigDecimal result = loanService.getSumOfCustomerLoans(customerId);

        // Then
        verify(loanRepository).getCustomerLoanSumByCustomerId(customerId);
        assertThat(result).isEqualTo(expectedSum);
    }
}
