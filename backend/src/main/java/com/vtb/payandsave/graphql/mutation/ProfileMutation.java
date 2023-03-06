package com.vtb.payandsave.graphql.mutation;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.request.ProfileRequest;
import com.vtb.payandsave.response.MessageResponse;
import com.vtb.payandsave.service.ProfileService;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
public class ProfileMutation implements GraphQLMutationResolver{
    final ProfileService profileService;

    public ProfileMutation(ProfileService profileService) {
        this.profileService = profileService;
    }

    public MessageResponse updateProfile(@GraphQLNonNull ProfileRequest profileRequest) {
        Object object = getContext().getAuthentication().getPrincipal();
        if(object instanceof Account) {
            Account account = (Account) object;
            return profileService.update(account, profileRequest);
        } else {
            return null;
        }
    }
}
