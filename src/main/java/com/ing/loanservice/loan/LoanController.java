package com.ing.loanservice.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/loan-provider")
public class LoanController {
    private final LoanService loanService;


    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }



    @PostMapping(value = "/loan",
                  consumes = "application/json",
                  produces = "application/json")
    public ResponseEntity<String> loanRegisteration(
            @RequestBody LoanRequest loanRequest) {

        return new ResponseEntity<>(
                loanService.registerNewLoan(loanRequest).getReferenceNo()
                , HttpStatus.CREATED);
    }

    @GetMapping(value = "/{customerId}/sum",
            consumes = "application/json",
            produces = "application/json")
    public Long loanRegisteration(
            @PathVariable("customerId") String customerId) {
        System.out.println(customerId);
        return 1L;
    }

}
