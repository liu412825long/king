package cn.bj.king.service.impl;

import cn.bj.king.entity.User;
import cn.bj.king.mapper.master.UserMapper;
import cn.bj.king.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
