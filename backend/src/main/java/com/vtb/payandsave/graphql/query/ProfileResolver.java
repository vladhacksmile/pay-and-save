package com.vtb.payandsave.graphql.query;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.response.ProfileResponse;
import com.vtb.payandsave.service.ProfileService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
public class ProfileResolver implements GraphQLQueryResolver {
    final ProfileService profileService;

    @Autowired
    public ProfileResolver(ProfileService profileService) {
        this.profileService = profileService;
    }

    public ProfileResponse getProfile() {
        Object object = getContext().getAuthentication().getPrincipal();
        if(object instanceof Account) {
            Account account = (Account) object;
            return profileService.get(account);
        } else {
            return null;
        }
    }

}
