package com.devsu.bank.ms.accounts.domains.commons.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongMovementException extends RuntimeException {
    public WrongMovementException(String message) {
        super(message);
    }
}
