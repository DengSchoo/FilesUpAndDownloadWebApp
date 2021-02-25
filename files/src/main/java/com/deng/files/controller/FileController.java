package com.deng.files.controller;

import com.deng.files.entity.User;
import com.deng.files.entity.UserFile;
import com.deng.files.service.UserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    private UserFileService userFileService;
    /**
     * 展示文件信息
     */
    @GetMapping("findAllFiles")
    public String findAll(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<UserFile> byUserId = userFileService.findByUserId(user.getId());
        // 存入作用域
        model.addAttribute("files", byUserId);
        return "showAllFiles";
    }

}
