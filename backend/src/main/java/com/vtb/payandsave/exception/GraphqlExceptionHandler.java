package com.vtb.payandsave.exception;

import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class GraphqlExceptionHandler {
    @ExceptionHandler({RuntimeException.class})
    public ThrowableGraphQLError handle(RuntimeException e) {
        return new ThrowableGraphQLError(e);
    }
}
