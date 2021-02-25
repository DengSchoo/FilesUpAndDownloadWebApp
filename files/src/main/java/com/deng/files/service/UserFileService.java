package com.deng.files.service;

import com.deng.files.dao.UserDao;
import com.deng.files.dao.UserFileDao;
import com.deng.files.entity.User;
import com.deng.files.entity.UserFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface UserFileService {
    public List<UserFile> findByUserId(Integer id);

}
