package com.ing.loanservice.loan;

import com.ing.loanservice.loan.exceptions.LoanNotValidException;
import com.ing.loanservice.loan.utils.RestExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

import static java.util.Optional.ofNullable;

@Service
public class LoanService {
    private final String CACHE_NAME="customerSum";
    private final BigDecimal MIN_LOAN_VALUE=BigDecimal.valueOf(500);
    private final BigDecimal MAX_LOAN_VALUE=BigDecimal.valueOf(12000.50);
    private final LoanRepository loanRepository;
    private final CacheManager cacheManager;
    private static final Logger log =
            LoggerFactory.getLogger(RestExceptionHandler.class);


    @Autowired
    public LoanService(LoanRepository loanRepository,
                       CacheManager cacheManager) {

        this.loanRepository = loanRepository;
        this.cacheManager=cacheManager;
    }

    public Loan registerNewLoan(LoanRequest loanRequest) {


        //If Previous Sum is Cached not Cache must be Invalidated

        Loan loan=createLoanFromRequest(loanRequest);
        validateLoan(loan);
        ofNullable(cacheManager.getCache(CACHE_NAME)).ifPresent(
                cache ->cache.evictIfPresent(loanRequest.getCustomerId()));
        log.info("Cache is Evicted");

        return loanRepository.save(loan);
    }


    public BigDecimal getSumOfCustomerLoans(Long customerId) {
        //Check if sum exist in cache
        Optional<BigDecimal> sum=checkCacheForSum(customerId);
        if (sum.isPresent()){
            log.info(
                    String.format("Sum is Returned For CustomerId:%s From Chache",customerId));
            return sum.get();
        }
        //Update Cache Value
        BigDecimal customerLoansSum= loanRepository.getCustomerLoanSumByCustomerId(customerId);
        ofNullable(cacheManager.getCache(CACHE_NAME)).ifPresent(
                cache ->cache.put(customerId,customerLoansSum));
        log.info(String.format("Cache is Updated For CustomerId:%s",customerId);
        return customerLoansSum;
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
    private void validateLoan(Loan loan) {
        if (loan.getAmount().compareTo(MIN_LOAN_VALUE)<0  || loan.getAmount().compareTo(MAX_LOAN_VALUE)>0 )
            throw new LoanNotValidException(loan.getAmount());
    }
    private Optional<BigDecimal> checkCacheForSum(Long customerId){
        return ofNullable(cacheManager.getCache(CACHE_NAME))
                .map(c -> c.get(customerId, BigDecimal.class));


    }


}
