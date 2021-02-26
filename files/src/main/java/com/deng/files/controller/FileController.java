package com.deng.files.controller;

import com.deng.files.entity.User;
import com.deng.files.entity.UserFile;
import com.deng.files.service.UserFileService;
import com.sun.deploy.net.HttpResponse;
import com.sun.deploy.net.URLEncoder;
import lombok.val;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    private UserFileService userFileService;

    /**
     * 返回当前用户的所有文件列表 --- json格式数据
     */
    @GetMapping("findAllJSON")
    @ResponseBody
    public List<UserFile> findAllJSON(HttpSession session, Model model){
        // 获取登录id
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return null;
        }
        List<UserFile> userFiles = userFileService.findByUserId(user.getId());
        return userFiles;
    }
    /**
     * 删除文件信息
     */
    @GetMapping("delete")
    public String delete(Integer id) throws FileNotFoundException {
        // 根据id查询信息
        UserFile userFile = userFileService.findById(id);
        String realPath = ResourceUtils.getURL("classpath:").getPath() + "/static" + userFile.getPath();
        File file = new File(realPath , userFile.getNewFileName());
        if (file.exists()) {
            file.delete();
        }
        // 删除数据库
        userFileService.delete(id);
        return "redirect:/file/findAllFiles";
    }
    /**
     * 文件下载
     */
    @GetMapping("download")
    public void download( Integer id, String openWay, HttpServletResponse response) throws IOException {

        // 获取文件信息
        UserFile userFile = userFileService.findById(id);
        // 更新下载次数
        if(openWay == null) {
            openWay = "attachment";
        }
        if ( "attachment".equals(openWay)) {
            userFile.setDownCounts(userFile.getDownCounts() + 1);
        }
        userFileService.update(userFile);
        // 根据文件信息获取文件名和存储路径 获取文件输入流
        String realpath = ResourceUtils.getURL("classpath:").getPath() + "/static/" + userFile.getPath();
        FileInputStream is = new FileInputStream(new File(realpath, userFile.getNewFileName()));
        // 附件下载 或 在线打开
        openWay =  openWay +";filename=";
        response.setHeader("content-disposition", openWay
                + URLEncoder.encode(userFile.getOldFileName(), "UTF-8"));
        // 获取响应输出流
        ServletOutputStream os = response.getOutputStream();

        // 文件拷贝
        IOUtils.copy(is, os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);

    }

    /**
     * 上传文件
     */
    @PostMapping("upload")
    public String upload(MultipartFile file, HttpSession session) throws IOException {
        Date date = new Date();
        String oldFileName = file.getOriginalFilename();
        // 获取文件后缀
        String ext = "." + FilenameUtils.getExtension(oldFileName);
        // 生成新的文件名
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(date)
                + UUID.randomUUID().toString().replace("-","")
                + ext;
        // 文件大小
        Long size = file.getSize();

        // 文件类型
        String type = file.getContentType();

        // 创建文件夹 根据日期生成dir
        String path = "/static/files";
        String realPath = ResourceUtils.getURL("classpath:").getPath() + path;
        String curDay = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String dataDirPath = realPath + "/" + curDay;
        File dateDir = new File(dataDirPath);
        if (!dateDir.exists()) {
            dateDir.mkdirs();
        }

        // 处理文件上传
        file.transferTo(new File(dataDirPath, newFileName));

        // 将文件信息放入持久化
        User user = (User)session.getAttribute("user");
        UserFile userFile = new UserFile();
        userFile.setOldFileName(oldFileName).setNewFileName(newFileName).
                setExt(ext).setSize(String.valueOf(size)).setUploadTime(date).
                setPath("files/" + curDay).setType(type).setUserId(user.getId());
        userFileService.save(userFile);
        return "redirect:/file/findAllFiles";
    }


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
