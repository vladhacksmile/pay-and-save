package com.vtb.payandsave.graphql.query;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.entity.Target;
import com.vtb.payandsave.exception.TargetNotFoundException;
import com.vtb.payandsave.service.TargetService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
public class TargetResolver implements GraphQLQueryResolver {

    final TargetService targetService;

    @Autowired
    public TargetResolver(TargetService targetService) {
        this.targetService = targetService;
    }

    public Set<Target> getAllTargets() {
        Object object = getContext().getAuthentication().getPrincipal();
        if(object instanceof Account) {
            Account account = (Account) object;
            return account.getTargets();
        } else {
            return null;
        }
    }

    private Target targetById(Account account, long id) {
        return account.getTargets().stream().filter(target -> target.getTarget_id().equals(id)).findFirst().orElseThrow(TargetNotFoundException::new);
    }

    public Target getTargetById(long id) {
        Object object = getContext().getAuthentication().getPrincipal();
        if (object instanceof Account) {
            Account account = (Account) object;
            return targetById(account, id);
        } else {
            return null;
        }
    }
}
