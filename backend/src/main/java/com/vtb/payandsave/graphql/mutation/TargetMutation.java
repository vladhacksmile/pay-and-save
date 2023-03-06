package com.vtb.payandsave.graphql.mutation;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.entity.Target;
import com.vtb.payandsave.entity.card.Card;
import com.vtb.payandsave.exception.CardNotFoundException;
import com.vtb.payandsave.exception.TargetNotFoundException;
import com.vtb.payandsave.request.card.CardReplenishmentRequest;
import com.vtb.payandsave.request.card.CardRequest;
import com.vtb.payandsave.request.card.CardSettingsRequest;
import com.vtb.payandsave.request.card.PayByCardRequest;
import com.vtb.payandsave.request.target.TargetReplenishmentRequest;
import com.vtb.payandsave.request.target.TargetRequest;
import com.vtb.payandsave.request.target.TargetWithdrawRequest;
import com.vtb.payandsave.response.MessageResponse;
import com.vtb.payandsave.service.CardService;
import com.vtb.payandsave.service.TargetService;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
public class TargetMutation implements GraphQLMutationResolver {
    final TargetService targetService;

    public TargetMutation(TargetService targetService) {
        this.targetService = targetService;
    }

    public MessageResponse addTarget(@GraphQLNonNull TargetRequest targetRequest) {
        Object object = getContext().getAuthentication().getPrincipal();
        if(object instanceof Account) {
            Account account = (Account) object;
            return targetService.add(account, targetRequest);
        } else {
            return null;
        }
    }

    public MessageResponse replenishTargetById(@GraphQLNonNull long id, @GraphQLNonNull TargetReplenishmentRequest targetReplenishmentRequest) {
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

    public MessageResponse withdrawTargetById(@GraphQLNonNull long id, @GraphQLNonNull TargetWithdrawRequest targetWithdrawRequest) {
        Object object = getContext().getAuthentication().getPrincipal();
        if(object instanceof Account) {
            Account account = (Account) object;
            return targetService.withdraw(account, targetById(account, id), targetWithdrawRequest);
        } else {
            return null;
        }
    }

    public MessageResponse updateTargetById(@GraphQLNonNull long id, @GraphQLNonNull TargetRequest targetRequest) {
        Object object = getContext().getAuthentication().getPrincipal();
        if(object instanceof Account) {
            Account account = (Account) object;
            return targetService.update(account, targetById(account, id), targetRequest);
        } else {
            return null;
        }
    }
}
