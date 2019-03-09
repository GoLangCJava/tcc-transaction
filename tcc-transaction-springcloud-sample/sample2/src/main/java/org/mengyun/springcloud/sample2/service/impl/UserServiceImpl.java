package org.mengyun.springcloud.sample2.service.impl;

import org.mengyun.springcloud.sample2.entity.User;
import org.mengyun.springcloud.sample2.mapper.UserMapper;
import org.mengyun.springcloud.sample2.service.IUserService;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper mapper;

    @Override
    @Compensable(confirmMethod = "confirmSave", cancelMethod = "cancelSave")
    public void save(TransactionContext transactionContext, User user) {
        mapper.insert(user);
    }

    @Override
    public void confirmSave(TransactionContext transactionContext, User user) {
        System.out.println("--------------UserServiceImpl.confirmSave");
    }

    @Override
    public void cancelSave(TransactionContext transactionContext, User user) {
        System.out.println("--------------UserServiceImpl.cancelSave");
        mapper.deleteByPrimaryKey(user.getId());
    }
}