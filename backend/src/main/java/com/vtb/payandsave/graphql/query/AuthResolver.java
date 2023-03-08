package com.vtb.payandsave.graphql.query;

import com.vtb.payandsave.request.auth.LoginRequest;
import com.vtb.payandsave.response.JwtResponse;
import com.vtb.payandsave.service.AuthService;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthResolver implements GraphQLQueryResolver {
    final AuthService authService;

    @Autowired
    public AuthResolver(AuthService authService) {
        this.authService = authService;
    }

    public JwtResponse authUser(@GraphQLNonNull LoginRequest loginRequest) {
        return authService.authUser(loginRequest);
    }
}
