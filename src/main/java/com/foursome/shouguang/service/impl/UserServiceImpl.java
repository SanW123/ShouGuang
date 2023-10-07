package com.foursome.shouguang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.foursome.shouguang.entity.User;
import com.foursome.shouguang.mapper.UserMapper;
import com.foursome.shouguang.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
