package org.mengyun.springcloud.sample1.service.impl;

import org.mengyun.springcloud.sample1.entity.User;
import org.mengyun.springcloud.sample1.entity.UserParamWarp;
import org.mengyun.springcloud.sample1.feign.Sample2UserFeignClient;
import org.mengyun.springcloud.sample1.mapper.UserMapper;
import org.mengyun.springcloud.sample1.service.IUserService;
import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper mapper;
    @Autowired
    private Sample2UserFeignClient sample2UserFeignClient;

    @Override
    @Compensable(confirmMethod = "confirmSave", cancelMethod = "cancelSave")
    public void save(User user) {

        UserParamWarp userParamWarp = new UserParamWarp();
        userParamWarp.setUser(user);
        Map<String, Object> map = sample2UserFeignClient.save(userParamWarp);
        int status = (int) map.get("status");
        if (status == 1) {
            throw new RuntimeException((String) map.get("msg"));
        }

        mapper.insert(user);
    }

    @Override
    public void confirmSave(User user) {
        System.out.println("-------------UserServiceImpl.confirmSave");
    }

    @Override
    public void cancelSave(User user) {
        System.out.println("-------------UserServiceImpl.cancelSave");
        mapper.deleteByPrimaryKey(user.getId());
    }
}