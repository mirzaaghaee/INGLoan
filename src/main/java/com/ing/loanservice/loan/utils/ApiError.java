package com.ing.loanservice.loan.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


class ApiError {

    @Getter
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    @Getter
    private String message;
    private String debugMessage;
    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}