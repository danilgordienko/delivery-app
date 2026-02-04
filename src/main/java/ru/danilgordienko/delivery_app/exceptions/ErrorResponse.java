package ru.danilgordienko.delivery_app.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ErrorResponse {
    private String error;
    private String message;
    private int code;
    private Instant timestamp;

    public ErrorResponse(int code, String error, String message) {
        this.timestamp = Instant.now();
        this.code = code;
        this.error = error;
        this.message = message;
    }
}
