package org.mengyun.tcctransaction.utils;

import org.mengyun.tcctransaction.api.Propagation;
import org.mengyun.tcctransaction.api.TransactionContext;

/**
 * Created by changming.xie on 2/23/17.
 */
public class TransactionUtils {

    public static boolean isLegalTransactionContext(boolean isTransactionActive, Propagation propagation, TransactionContext transactionContext) {

        return !propagation.equals(Propagation.MANDATORY) || isTransactionActive || null != transactionContext;
    }
}