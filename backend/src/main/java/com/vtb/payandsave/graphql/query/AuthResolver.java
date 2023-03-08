package com.vtb.payandsave.graphql.query;

import com.vtb.payandsave.request.auth.LoginRequest;
import com.vtb.payandsave.response.JwtResponse;
import com.vtb.payandsave.service.AuthService;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
@Validated
public class AuthResolver implements GraphQLQueryResolver {
    final AuthService authService;

    @Autowired
    public AuthResolver(AuthService authService) {
        this.authService = authService;
    }

    public JwtResponse authUser(@Valid @GraphQLNonNull LoginRequest loginRequest) {
        return authService.authUser(loginRequest);
    }
}
