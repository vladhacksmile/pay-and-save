package com.vtb.payandsave.graphql.query;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.entity.card.Card;
import com.vtb.payandsave.exception.CardNotFoundException;
import com.vtb.payandsave.response.CardResponse;
import com.vtb.payandsave.response.ProfileResponse;
import com.vtb.payandsave.service.CardService;
import com.vtb.payandsave.service.ProfileService;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
public class CardResolver implements GraphQLQueryResolver {
    final CardService cardService;

    public CardResolver(CardService cardService) {
        this.cardService = cardService;
    }

    public Set<Card> getAllCards() {
        Object object = getContext().getAuthentication().getPrincipal();
        if(object instanceof Account) {
            Account account = (Account) object;
            return account.getCards();
        } else {
            return null;
        }
    }

    private Card cardById(Account account, long id) {
        return account.getCards().stream().filter(card -> card.getCard_id().equals(id)).findFirst().orElseThrow(CardNotFoundException::new);
    }

    public CardResponse getCardSettings(@GraphQLNonNull long id) {
        Object object = getContext().getAuthentication().getPrincipal();
        if (object instanceof Account) {
            Account account = (Account) object;
            return cardService.getCardSettings(account, cardById(account, id));
        } else {
            return null;
        }
    }

    public Card getCardById(@GraphQLNonNull long id) {
        Object object = getContext().getAuthentication().getPrincipal();
        if (object instanceof Account) {
            Account account = (Account) object;
            return cardById(account, id);
        } else {
            return null;
        }
    }
}
