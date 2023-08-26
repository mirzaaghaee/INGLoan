package com.ing.loanservice.loan;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
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

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", amount=" + amount +
                ", customerId=" + customerId +
                ", customerFullName='" + customerFullName + '\'' +
                '}';
    }
}
