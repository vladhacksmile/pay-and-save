package com.vtb.payandsave.exception;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException() {
        super("Card not found!", null, false, false);
    }
}
