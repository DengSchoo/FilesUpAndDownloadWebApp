package com.deng.files.dao;

import com.deng.files.entity.UserFile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFileDao {
    // 根据用户ID获取文件列表
    List<UserFile> findByUserId(Integer id);
    // 保存用户的文件记录
    void save(UserFile userFile);

    // 根据ID查询文件
    UserFile findById(Integer id);

    // 更新下载次数
    void update(UserFile userFile);

    // 根据id删除
    void delete(Integer id);
}
