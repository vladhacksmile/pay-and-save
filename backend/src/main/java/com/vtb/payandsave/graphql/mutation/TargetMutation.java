package com.vtb.payandsave.graphql.mutation;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.entity.Target;
import com.vtb.payandsave.exception.TargetNotFoundException;
import com.vtb.payandsave.request.target.TargetReplenishmentRequest;
import com.vtb.payandsave.request.target.TargetRequest;
import com.vtb.payandsave.request.target.TargetWithdrawRequest;
import com.vtb.payandsave.response.MessageResponse;
import com.vtb.payandsave.service.TargetService;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
@Validated
public class TargetMutation implements GraphQLMutationResolver {
    final TargetService targetService;

    public TargetMutation(TargetService targetService) {
        this.targetService = targetService;
    }

    public MessageResponse addTarget(@Valid @GraphQLNonNull TargetRequest targetRequest) {
        Object object = getContext().getAuthentication().getPrincipal();
        if(object instanceof Account) {
            Account account = (Account) object;
            return targetService.add(account, targetRequest);
        } else {
            return null;
        }
    }

    public MessageResponse replenishTargetById(long id, @Valid@GraphQLNonNull TargetReplenishmentRequest targetReplenishmentRequest) {
        Object object = getContext().getAuthentication().getPrincipal();
        if(object instanceof Account) {
            Account account = (Account) object;
            return targetService.replenishment(account, targetById(account, id), targetReplenishmentRequest);
        } else {
            return null;
        }
    }

    private Target targetById(Account account, long id) {
        return account.getTargets().stream().filter(target -> target.getTarget_id().equals(id)).findFirst().orElseThrow(TargetNotFoundException::new);
    }

    public MessageResponse withdrawTargetById(long id, @Valid @GraphQLNonNull TargetWithdrawRequest targetWithdrawRequest) {
        Object object = getContext().getAuthentication().getPrincipal();
        if(object instanceof Account) {
            Account account = (Account) object;
            return targetService.withdraw(account, targetById(account, id), targetWithdrawRequest);
        } else {
            return null;
        }
    }

    public MessageResponse updateTargetById(long id, @Valid @GraphQLNonNull TargetRequest targetRequest) {
        Object object = getContext().getAuthentication().getPrincipal();
        if(object instanceof Account) {
            Account account = (Account) object;
            return targetService.update(account, targetById(account, id), targetRequest);
        } else {
            return null;
        }
    }
}
