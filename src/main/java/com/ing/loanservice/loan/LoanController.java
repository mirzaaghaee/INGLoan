package com.ing.loanservice.loan;

import com.ing.loanservice.loan.exceptions.LoanNotValidException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/loan-provider")
public class LoanController {
    @PutMapping(value = "/loan", consumes = "application/json", produces = "application/json")
    public Integer loanRegisteration(@RequestBody LoanRequest loanRequest) {
        return 1;
    }

    @GetMapping(value = "/{customerId}/loans", consumes = "application/json", produces = "application/json")
    public Long loanRegisteration(@PathVariable("customerId") String customerId) {
        System.out.println(customerId);
        return 1L;
    }

}
