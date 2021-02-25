package com.deng.files.service;

import com.deng.files.dao.UserFileDao;
import com.deng.files.entity.UserFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserFileServiceImpl implements UserFileService{
    @Autowired
    private UserFileDao userFileDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<UserFile> findByUserId(Integer id) {
        return userFileDao.findByUserId(id);
    }
}
