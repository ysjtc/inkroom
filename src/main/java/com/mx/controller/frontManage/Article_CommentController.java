package com.mx.controller.frontManage;

import com.mx.pojo.Article_Comment;
import com.mx.service.Article_CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
/*
 *Comments评论控制器
 */
@RequestMapping("/Comment")

public class Article_CommentController {

    @Autowired
    private Article_CommentService article_commentService;

    @RequestMapping("/query/aId")
    public String queryCommentByaId(Integer aId, HttpSession session){
        List<Article_Comment> list = article_commentService.queryCommentByaId(aId);
        session.setAttribute("comments",list);
        return "comments";
    }

    @RequestMapping("/query/uId")
    public String queryCommentByuId(Integer uId, HttpSession session){
        List<Article_Comment> list = article_commentService.queryCommentByuId(uId);
        session.setAttribute("comments",list);
        return "comments";
    }

    @RequestMapping("/addTo")
    public String addTo(Integer aId, HttpSession session){
        session.setAttribute("aId",aId);
        return "addComment";
    }


    @RequestMapping("/add")
    public String add(Article_Comment article_comment,HttpSession session){
        boolean suc = article_commentService.add(article_comment);
        List<Article_Comment> list = article_commentService.queryCommentByaId(article_comment.getaId());
        session.setAttribute("comments",list);
        return "comments";
    }
    @ResponseBody
    @RequestMapping("/queryCByaId")
    public List<Article_Comment> queryCByaId(Integer aId,HttpSession session){
        List<Article_Comment> l=article_commentService.queryCByA_id(aId);
        session.setAttribute("qeuryCByaId",l);
        return l;
    }

    @RequestMapping("/cmtAndUser")
    public String cmtAndUser(Integer aId, HttpServletRequest req,HttpSession session){
        List<Map<String,Object>> l=null;
        if (aId==null){
            int Id= Integer.parseInt(req.getAttribute("aId").toString());
            l=article_commentService.cmtAndUser(Id);
        }else {
            l = article_commentService.cmtAndUser(aId);
        }
        session.setAttribute("cmtAndUser",l);
        System.out.println(l);
        return "frontShow/club/articleDetail";
    }

    @RequestMapping("/addAC")
    public void addAC(Article_Comment article_comment, HttpServletResponse response, HttpServletRequest request) throws IOException {
        int suc = article_commentService.addAC(article_comment);
        //List<Article_Comment> list = article_commentService.queryCommentByaId(article_comment.getaId());
        //session.setAttribute("comments",list);
        response.sendRedirect(request.getContextPath()+"/Article/query/aId?id="+article_comment.getaId());
    }
    @ResponseBody
    @RequestMapping("/query/reply")
    public List<Map<String,Object>> reply(Integer acoId,HttpSession session){
        int aId=Integer.parseInt(session.getAttribute("aId").toString());
        List<Map<String,Object>> l=article_commentService.reply(aId,acoId);
        session.setAttribute("replyDetail",l);
        return l;
    }

    @ResponseBody
    @RequestMapping("/addReply")
    public boolean addReply(String acoId,Integer to_uid,String acr_content,HttpSession session){
        Map<String,Object> map=new HashMap<>();
        Integer a_id=Integer.parseInt(session.getAttribute("aId").toString());
        Integer u_id=Integer.parseInt(session.getAttribute("uId").toString());
        map.put("a_id",session.getAttribute("aId").toString());
        map.put("u_id",session.getAttribute("uId").toString());
        System.out.println("acoId"+acoId+"to_uid"+to_uid);
        map.put("aco_id",acoId);
        map.put("to_uid",to_uid);
        map.put("acr_content",acr_content);
        boolean flag=true;
        if(article_commentService.addReply(map)!=1){
            flag=false;
        }
        return flag;
    }

    @ResponseBody
    @RequestMapping("/delReply")
    public boolean delReply(Integer acr_id){
        boolean flag=true;
        if(article_commentService.delReply(acr_id)!=1){
            flag=false;
        }
        return flag;
    }
    @ResponseBody
    @RequestMapping("/delAC")
    public boolean delAC(Integer aco_id){
        boolean flag=true;
        if (article_commentService.delAC(aco_id)!=1){
            flag=false;
        }
        return flag;
    }
    @ResponseBody
    @RequestMapping("/query/myComment")
    public List<Map<String,Object>> myComment(Integer uId,HttpSession session){
        if (uId==null){
            uId=Integer.parseInt(session.getAttribute("uId").toString());
        }
        List<Map<String,Object>> l=article_commentService.myComment(uId);
        return l;
    }

    @ResponseBody
    @RequestMapping("/query/myReply")
    public List<Map<String,Object>> myReply(HttpSession session,Integer uId){
        if (uId==null){
            uId=Integer.parseInt(session.getAttribute("uId").toString());
        }
        return article_commentService.myReply(uId);
    }
}
