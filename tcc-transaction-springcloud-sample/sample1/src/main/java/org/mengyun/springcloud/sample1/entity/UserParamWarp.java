package org.mengyun.springcloud.sample1.entity;

import org.mengyun.tcctransaction.api.TransactionContext;
import org.mengyun.tcctransaction.context.TransactionContextAware;

public class UserParamWarp implements TransactionContextAware {

    private TransactionContext transactionContext;
    private User user;

    @Override
    public TransactionContext getTransactionContext() {
        return transactionContext;
    }

    @Override
    public void setTransactionContext(TransactionContext transactionContext) {
        this.transactionContext = transactionContext;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}