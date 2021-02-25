package com.deng.files.dao;

import com.deng.files.entity.UserFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFileDao {
    // 根据用户ID获取文件列表
    List<UserFile> findByUserId(Integer id);
}
