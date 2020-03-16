package com.mx.utils.UpLoad;/*
@author 郭子雄
@description 提供用户头像上传的方法
*/

import com.mx.pojo.User;
import com.mx.pojo.User_Pic;
import com.mx.service.UserPicService;
import com.mx.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class UserUpload {
    /*注册时图片上传*/
    public static User_Pic imgUpload(
            MultipartFile file,
            HttpServletRequest request,
            User user,
            UserService userService,
            UserPicService userPicService) throws IOException {
        if(!file.isEmpty()) {
            User_Pic userPic=new User_Pic();
            //文件后缀名
            String suffixName=file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            // 新文件名
            String newFileName = user.getName()+suffixName;
            // 获得项目的路径
            ServletContext sc = request.getSession().getServletContext();
            // 上传位置(绝对物理路径)
            String path = sc.getRealPath("/static/upload/userPic"); // 设定文件保存的目录
            //File f = new File(path);
            System.out.println("---------------------------");
            System.out.println(path);
            File targetfile = new File(path,newFileName);

            System.out.println(targetfile.toString());
            /*判断目录是否存在，不存在则创建*/
            if (!targetfile.exists()){
                targetfile.mkdirs();
            }
            /*将图片存入在服务器下的制定路径（虚拟的）*/
            file.transferTo(targetfile);

            /*将图片路径存入数据库*/
            if(userPicService.queryById(userService.getUserIdByname(user.getName()))==null){
                userPic.setuId(userService.getUserIdByname(user.getName()));
                userPic.setUserPath("static/upload/userPic/"+newFileName);
                userPicService.addUserPic(userPic);
            }else{
                userPic.setuId(userService.getUserIdByname(user.getName()));
                userPic.setUserPath("static/upload/userPic/"+newFileName);
                userPicService.updateUserPic(userPic);
            }
            return userPic;
        }
        return null;
    }

}
