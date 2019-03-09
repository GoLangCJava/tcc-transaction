package org.mengyun.springcloud.sample2.service;

import org.mengyun.springcloud.sample2.entity.User;
import org.mengyun.tcctransaction.api.TransactionContext;

public interface IUserService {

    void save(TransactionContext transactionContext, User user);

    void confirmSave(TransactionContext transactionContext, User user);

    void cancelSave(TransactionContext transactionContext, User user);
}