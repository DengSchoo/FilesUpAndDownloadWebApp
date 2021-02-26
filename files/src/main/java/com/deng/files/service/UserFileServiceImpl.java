package com.deng.files.service;

import com.deng.files.dao.UserFileDao;
import com.deng.files.entity.User;
import com.deng.files.entity.UserFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserFileServiceImpl implements UserFileService{
    @Autowired
    private UserFileDao userFileDao;

    @Override
    public void delete(Integer id) {
        userFileDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public UserFile findById(Integer id) {
        UserFile userFile = userFileDao.findById(id);
        return userFile;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void update(UserFile userFile) {
        userFileDao.update(userFile);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<UserFile> findByUserId(Integer id) {
        return userFileDao.findByUserId(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void save(UserFile userFile) {
        userFile.setDownCounts(0);

        String isImg = userFile.getType().startsWith("image")?"是":"否";
        userFile.setIsImg(isImg);
        userFileDao.save(userFile);
    }
}
