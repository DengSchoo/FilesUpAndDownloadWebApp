package com.deng.files.dao;

import com.deng.files.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User login(User user);
}
