package com.ing.loanservice.loan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.loanservice.LoanApplication;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.assertj.core.api.Assertions.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = LoanApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class LoanControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LoanService loanService;

    @Test
    public void It_Should_Get_A_LoanRequest_And_Return_ReferenceNo_To_Customer() throws Exception {
        //Given a Loan Request
        LoanRequest loanRequest = new LoanRequest(BigDecimal.valueOf(5000), 1, "John Doe");

        //When
        var result= mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/loan-provider/loan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanRequest)));


        //Then
        result.andExpect(status().is(201));
        assertThat(result.andReturn().getResponse().getContentLength()).isEqualTo(18);
    }

    @Test
    public void It_Should_Get_A_CustomerId_And_Calculate_Sum_Of_Requested_Loan_By_Customer() throws Exception {

        //Given a CustomerId
        Long customerId = 1L;


        //When Get Sum of their loan
        BigDecimal sum =loanService.getSumOfCustomerLoans(customerId);

        //Then must be equal to what controller get
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/loan-provider/" + customerId + "/sum")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("sum is:" + sum.toString()));
    }
}
