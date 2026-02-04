package ru.danilgordienko.delivery_app.exceptions;

public class ProviderNotFoundException extends RuntimeException {
    public ProviderNotFoundException(final String message) {
        super(message);
    }
}
