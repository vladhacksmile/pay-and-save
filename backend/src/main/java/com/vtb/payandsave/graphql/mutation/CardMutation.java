package com.vtb.payandsave.graphql.mutation;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.entity.card.Card;
import com.vtb.payandsave.exception.CardNotFoundException;
import com.vtb.payandsave.exception.ExceptionMessages;
import com.vtb.payandsave.request.card.CardReplenishmentRequest;
import com.vtb.payandsave.request.card.CardRequest;
import com.vtb.payandsave.request.card.CardSettingsRequest;
import com.vtb.payandsave.request.card.PayByCardRequest;
import com.vtb.payandsave.response.MessageResponse;
import com.vtb.payandsave.service.CardService;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
@Validated
public class CardMutation implements GraphQLMutationResolver {
    final CardService cardService;

    public CardMutation(CardService cardService) {
        this.cardService = cardService;
    }

    public MessageResponse addCard(@Valid @GraphQLNonNull CardRequest cardRequest) {
        Object object = getContext().getAuthentication().getPrincipal();
        if(object instanceof Account) {
            Account account = (Account) object;
            return cardService.add(account, cardRequest);
        } else {
            return new MessageResponse(ExceptionMessages.ACCOUNT_NOT_DEFINED);
        }
    }

    public MessageResponse replenishCardById(long id, @Valid @GraphQLNonNull CardReplenishmentRequest cardReplenishmentRequest) {
        Object object = getContext().getAuthentication().getPrincipal();
        if(object instanceof Account) {
            Account account = (Account) object;
            return cardService.replenishCard(account, cardById(account, id), cardReplenishmentRequest);
        } else {
            return new MessageResponse(ExceptionMessages.ACCOUNT_NOT_DEFINED);
        }
    }

    private Card cardById(Account account, long id) throws CardNotFoundException {
        return account.getCards().stream().filter(card -> card.getCard_id().equals(id)).findFirst().orElseThrow(CardNotFoundException::new);
    }

    public MessageResponse payByCard(long id, @Valid @GraphQLNonNull PayByCardRequest payByCardRequest) {
        Object object = getContext().getAuthentication().getPrincipal();
        if(object instanceof Account) {
            Account account = (Account) object;
            return cardService.payByCard(account, cardById(account, id), payByCardRequest);
        } else {
            return new MessageResponse(ExceptionMessages.ACCOUNT_NOT_DEFINED);
        }
    }

    public MessageResponse changeCardSettings(long id, @Valid @GraphQLNonNull CardSettingsRequest cardSettingsRequest) {
        Object object = getContext().getAuthentication().getPrincipal();
        if(object instanceof Account) {
            Account account = (Account) object;
            return cardService.cardSettings(account, cardById(account, id), cardSettingsRequest);
        } else {
            return new MessageResponse(ExceptionMessages.ACCOUNT_NOT_DEFINED);
        }
    }
}
