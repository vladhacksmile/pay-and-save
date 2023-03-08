package com.vtb.payandsave.exception;

public class TargetNotFoundException extends RuntimeException {
    public TargetNotFoundException() {
        super("Target not found!", null, false, false);
    }
}
