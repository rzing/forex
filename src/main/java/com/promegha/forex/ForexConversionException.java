package com.promegha.forex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ForexConversionException extends Exception {
    public ForexConversionException(String message) {
        super(message);
    }
}
