package com.vtb.payandsave.exception;

import graphql.GraphQLError;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException() {
        super("Card not found!", null, false, false);
    }
}
