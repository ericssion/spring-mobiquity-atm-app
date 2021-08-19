package com.mobiquity.atm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AtmNotFoundException extends RuntimeException  {

	public AtmNotFoundException(String exception) {
        super(exception);
    }
}
