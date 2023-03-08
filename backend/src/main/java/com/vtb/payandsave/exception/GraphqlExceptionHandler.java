package com.vtb.payandsave.exception;

import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class GraphqlExceptionHandler {
    @ExceptionHandler({RuntimeException.class, ConstraintViolationException.class})
    public ThrowableGraphQLError handle(Exception e) {
        return new ThrowableGraphQLError(e);
    }
}
