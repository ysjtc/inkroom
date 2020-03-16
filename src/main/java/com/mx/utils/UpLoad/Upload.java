package com.mx.utils.UpLoad;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Upload {



    //文件上传
    public static void upLoads(MultipartFile[] multipartFiles, HttpSession session, String path){
        List<String> Pictures=new ArrayList<>();
        String ItemPicPath="";
//       1.判断是否为空，不为空才上传
        if (multipartFiles!=null&&multipartFiles.length>0){
            System.out.println(multipartFiles.length);
            for (MultipartFile multipartFile:multipartFiles) {
                if (multipartFile!=null&&!multipartFile.isEmpty()){
//            2.获取文件名
                    String picName=multipartFile.getOriginalFilename();
//            3.截取原文件前缀
                    String picNamePrifix=picName.substring(0,picName.lastIndexOf("."));
//            4.给文件加上时间戳
                    String NewpicNamePrifix=picNamePrifix+new Date().getTime();
//            5.给文件补充后缀
                    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
                    String fileDate = df.format(new Date());
                    String NewPicName=NewpicNamePrifix+picName.substring(picName.lastIndexOf("."));
//            6.构建新的文件
                    File file=new File(path+fileDate+File.separator+NewPicName);
//            7.上传
                    try {
                        //判断文件夹是否存在，不存在则创建
                        if (!file.exists()&& !file .isDirectory()){
                            file.mkdirs();
//                            if (!file.exists()) {
//                                file.createNewFile();
//                            }else {
//                                System.out.println("文件已存在");
//                            }
                        }
                            multipartFile.transferTo(file);
                            ItemPicPath=fileDate+File.separator+NewPicName;
                            Pictures.add(ItemPicPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        session.setAttribute("itemPicturePath",Pictures);
    }


}
