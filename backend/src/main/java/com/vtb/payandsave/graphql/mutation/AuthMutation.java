package com.vtb.payandsave.graphql.mutation;

import com.vtb.payandsave.request.auth.SignupRequest;
import com.vtb.payandsave.response.MessageResponse;
import com.vtb.payandsave.service.AuthService;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthMutation implements GraphQLMutationResolver {
    final AuthService authService;
    @Autowired
    public AuthMutation(AuthService authService) {
        this.authService = authService;
    }

    public MessageResponse registerUser(@GraphQLNonNull SignupRequest signupRequest) {
        return authService.registerUser(signupRequest);
    }
}
