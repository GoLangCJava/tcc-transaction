package org.mengyun.tcctransaction.context;

import org.mengyun.tcctransaction.api.TransactionContext;

/**
 * @author xiaobzhou
 * date 2019-01-15 23:04
 */
public interface TransactionContextAware {

    TransactionContext getTransactionContext();

    void setTransactionContext(TransactionContext transactionContext);
}