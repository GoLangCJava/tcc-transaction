package org.mengyun.springcloud.sample2.mapper;

import org.mengyun.springcloud.sample2.entity.User;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {

}