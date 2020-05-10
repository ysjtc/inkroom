package com.mx.controller.backMange;

import com.mx.service.ArticleService;
import com.mx.service.Article_CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
/*
 *Article控制器
 */
@RequestMapping("/ArticleBackManage")

public class ArticleManageController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private Article_CommentService article_commentService;

    @ResponseBody
    @RequestMapping("/query/allArticles")
    public List<Map<String,Object>> allArticles(Model model) {
        List l= articleService.articleManage();
        /*for (int i=0;i<l.size();i++) {
            Map<String,Object> map=(Map)l.get(i);
            Long timestamp = Long.parseLong(map.get("publish_time").toString());
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date(timestamp));
            map.put("publish_time",date);
            l.set(i,map);
        }*/
        return l;
    }

    @ResponseBody
    @RequestMapping("/query/Comments")
    public List<Map<String,Object>> Comments(Integer a_id){
        return article_commentService.cmtAndUser(a_id);
    }

    @ResponseBody
    @RequestMapping("/query/Reply")
    public List<Map<String,Object>> Reply(Integer aco_id){
        return article_commentService.reply(null,aco_id);
    }

    @ResponseBody
    @RequestMapping("/delete/Article")
    public Boolean delArtById(Integer a_id){
        if(articleService.delete(a_id)){
            return true;
        }else {
            return false;
        }
    }

    @ResponseBody
    @RequestMapping("/delete/Comment")
    public Boolean delComById(Integer aco_id){
        if(article_commentService.delAC(aco_id)!=0){
            return true;
        }else {
            return false;
        }
    }
    @ResponseBody
    @RequestMapping("/delete/Reply")
    public Boolean delRepById(Integer acr_id){
        if(article_commentService.delReply(acr_id)!=0){
            return true;
        }else {
            return false;
        }
    }
}
