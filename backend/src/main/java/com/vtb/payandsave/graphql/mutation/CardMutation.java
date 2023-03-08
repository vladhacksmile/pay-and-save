package com.vtb.payandsave.graphql.mutation;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.entity.card.Card;
import com.vtb.payandsave.exception.CardNotFoundException;
import com.vtb.payandsave.request.card.CardReplenishmentRequest;
import com.vtb.payandsave.request.card.CardRequest;
import com.vtb.payandsave.request.card.CardSettingsRequest;
import com.vtb.payandsave.request.card.PayByCardRequest;
import com.vtb.payandsave.response.CardResponse;
import com.vtb.payandsave.response.MessageResponse;
import com.vtb.payandsave.service.CardService;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
public class CardMutation implements GraphQLMutationResolver {
    final CardService cardService;

    public CardMutation(CardService cardService) {
        this.cardService = cardService;
    }

    public MessageResponse addCard(@GraphQLNonNull CardRequest cardRequest) {
        Object object = getContext().getAuthentication().getPrincipal();
        if(object instanceof Account) {
            Account account = (Account) object;
            return cardService.add(account, cardRequest);
        } else {
            return null;
        }
    }

    public MessageResponse replenishCardById(@GraphQLNonNull long id, @GraphQLNonNull CardReplenishmentRequest cardReplenishmentRequest) {
        Object object = getContext().getAuthentication().getPrincipal();
        if(object instanceof Account) {
            Account account = (Account) object;
            return cardService.replenishCard(account, cardById(account, id), cardReplenishmentRequest);
        } else {
            return null;
        }
    }

    private Card cardById(Account account, long id) throws CardNotFoundException {
        return account.getCards().stream().filter(card -> card.getCard_id().equals(id)).findFirst().orElseThrow(CardNotFoundException::new);
    }

    public MessageResponse payByCard(@GraphQLNonNull long id, @GraphQLNonNull PayByCardRequest payByCardRequest) {
        Object object = getContext().getAuthentication().getPrincipal();
        if(object instanceof Account) {
            Account account = (Account) object;
            return cardService.payByCard(account, cardById(account, id), payByCardRequest);
        } else {
            return null;
        }
    }

    public MessageResponse changeCardSettings(@GraphQLNonNull long id, @GraphQLNonNull CardSettingsRequest cardSettingsRequest) {
        Object object = getContext().getAuthentication().getPrincipal();
        if(object instanceof Account) {
            Account account = (Account) object;
            return cardService.cardSettings(account, cardById(account, id), cardSettingsRequest);
        } else {
            return null;
        }
    }
}
