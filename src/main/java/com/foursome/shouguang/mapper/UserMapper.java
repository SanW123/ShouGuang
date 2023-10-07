package com.foursome.shouguang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.foursome.shouguang.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
