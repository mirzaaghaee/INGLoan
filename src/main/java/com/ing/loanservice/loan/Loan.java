package com.ing.loanservice.loan;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@JsonIgnoreProperties(allowGetters = true)
@Getter
@Setter
public class Loan {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private BigDecimal amount;
    private Integer customerId;
    private String customerFullName;
    private String referenceNo;
    private LocalDateTime registrationTimeStamp;

    public Loan(BigDecimal amount, Integer customerId, String customerFullName, String referenceNo, LocalDateTime registrationTimeStamp) {
        this.amount = amount;
        this.customerId = customerId;
        this.customerFullName = customerFullName;
        this.referenceNo = referenceNo;
        this.registrationTimeStamp = registrationTimeStamp;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", amount=" + amount +
                ", customerId=" + customerId +
                ", customerFullName='" + customerFullName + '\'' +
                ", referenceNo='" + referenceNo + '\'' +
                ", registerationTimeStamp=" + registrationTimeStamp +
                '}';
    }
}
