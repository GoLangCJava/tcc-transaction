package org.mengyun.springcloud.sample1.service;

import org.mengyun.springcloud.sample1.entity.User;

public interface IUserService {

    void save(User user);

    void confirmSave(User user);

    void cancelSave(User user);
}