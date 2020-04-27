package com.mx.controller.frontManage;

import com.mx.pojo.Article;
import com.mx.pojo.Article_Category;
import com.mx.service.ArticleService;
import com.mx.service.Article_CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
/*
*Article控制器
 */
@RequestMapping("/Article")

public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private Article_CategoryService article_categoryService;

    @RequestMapping("/query/title")             //按标题模糊查询
    public String queryByTitle(String title, HttpSession session){
        session.setAttribute("title",title);
        List<Article> list = articleService.queryByTitle(title);
        session.setAttribute("article",list);
        return "frontShow/club/club";
    }
    @RequestMapping("/query/myInfo")
    public String queryByUId(Integer uId,HttpSession session,HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        if (uId==null){
             uId = Integer.parseInt(session.getAttribute("uId").toString());
        }else {
            session.setAttribute("others","uId");
        }
        Map<String,Object> statistics=articleService.statistics(uId);
        session.setAttribute("statistics",statistics);
        int p_c = articleService.myPraiseCount(uId);
        System.out.println(p_c);
        session.setAttribute("my_p_c", p_c);
        return "frontShow/club/mine";
    }


    @RequestMapping("/query/aId")          //点击标题进入文章页面
    public void queryArticle(String id, HttpSession session, HttpServletResponse res, HttpServletRequest req) throws IOException, ServletException {
        int aId=Integer.parseInt(id);
        String uname=(String)session.getAttribute("USER_ID");
        if (uname==null||uname.equals("")){
            //return "{\"result\":false,\"isLogin\":false}";
            res.sendRedirect(req.getContextPath()+"/FrontForward/loginMain");
        }else {
            session.setAttribute("aId",aId);
            Article list = articleService.queryArticle(aId);
            session.setAttribute("articleDetail",list);
            session.setAttribute("article_praise",articleService.articlePraise(aId));
            //String s=list.getContent();
            //s.replace("\r\n","<br/>").replace(" ","&nbsp;&nbsp;");
            //list.setContent(s);
            System.out.println(list);
            req.setAttribute("aId",aId);
            req.getRequestDispatcher("/Comment/cmtAndUser").forward(req,res);
        }
    }
    @ResponseBody
    @RequestMapping("/query/uId")          //用户文章页面
    public List<Article> queryArticleByuId(Integer uId,HttpSession session){
        session.setAttribute("uId",uId);
        List<Article> list = articleService.queryArticleByuId(uId);
        session.setAttribute("article",list);
        return list;
    }
    @ResponseBody
    @RequestMapping("/delete")          //删除文章+所删除文章评论
    public boolean delete(Integer aId,HttpSession session){
        boolean suc = articleService.delete(aId);
        return suc;
    }
    @ResponseBody
    @RequestMapping("/query/myPraise")          //我点赞的文章
    public List<Article> myPraise(Integer uId,HttpSession session){
        if (uId==null) {
            uId = Integer.parseInt(session.getAttribute("uId").toString());
        }
        List<Article> l=articleService.myPraiseArticle(uId);
        session.setAttribute("myPraise",l);
        return l;
    }

    //@ResponseBody  //按类别搜索
    @RequestMapping("/query/ctgr")
    public String queryByctgr(Integer acId,HttpSession session){
        //int acId=Integer.parseInt(id);
        List<Article> l=articleService.queryByctgr(acId);
        session.setAttribute("article",l);
        return "frontShow/club/club";
    }
    @RequestMapping("/addArticle")
    public String addArticle(String title,String content,Integer uId,Integer acId) throws ServletException, IOException {
        if(articleService.addArticle(title,content,uId,acId)!=0){
            return "redirect:query/conditions";
        }else {
            return "redirect:./../frontShow/club/addArticle";
        }
    }
    @RequestMapping("/smg")
    public void PVRaise(Integer aId,HttpSession session){
        session.setAttribute("aId",aId);
        articleService.PVRaise(aId);
    }
    @ResponseBody
    @RequestMapping("/addAP")
    public int addAP(Integer aId, HttpSession session){
        return articleService.addAP(aId,Integer.parseInt(session.getAttribute("uId").toString()));
    }

    @RequestMapping("/query/conditions")
    public String queryByConditons(HttpSession session,HttpServletRequest request,String title,Integer uId,String content,Integer acId,String uName, Integer pageNo,String pageView,String praiseCount,String publishTime,Integer pageSize){
        boolean flag=true;
        List<Article> l=new ArrayList<>();
        Map<String,Object> cache= new HashMap<>();
        Map<String,String> cach=new HashMap<>();
        String sort=null;
        if(pageSize==null){
            pageSize=5;
        }else {
            session.setAttribute("pageSize",pageSize);
        }
        if (session.getAttribute("pageSize")!=null){
            pageSize=Integer.parseInt(session.getAttribute("pageSize").toString());
        }
        if (pageView!=null || praiseCount!=null || publishTime!=null){
            pageNo=1;
            cach.put("pageView",pageView);
            cach.put("praiseCount",praiseCount);
            cach.put("publishTime",publishTime);
            session.setAttribute("cach",cach);
            System.out.println(pageView+"00"+praiseCount+"00"+publishTime);
        }else {
            if (pageNo==null){
                session.setAttribute("cach",null);
            }else if((Map)session.getAttribute("cach")!=null) {
                cach = (Map) session.getAttribute("cach");
                if (cach.get("pageView") != null) {
                    pageView = cach.get("pageView");
                }
                if (cach.get("praiseCount") != null) {
                    praiseCount = cach.get("praiseCount");
                }
                if (cach.get("publishTime") != null) {
                    publishTime = cach.get("publishTime");
                }
                System.out.println(pageView + "00" + praiseCount + "00" + publishTime);
            }
        }
        if (pageNo==null){
            session.setAttribute("cache",null);
            pageNo=1;
            cache.put("title",title);
            cache.put("uId",uId);
            cache.put("content",content);
            cache.put("acId",acId);
            cache.put("uName",uName);
            session.setAttribute("cache",cache);
            System.out.println(cache+"======================");
        }else {
            flag=false;
            cache=(Map)session.getAttribute("cache");
            System.out.println(cache+"======================"+session.getAttribute("cache"));
            if(cache.get("title")!=null){
                title=cache.get("title").toString();
            }
            if(cache.get("uId")!=null){
                uId=Integer.parseInt(cache.get("uId").toString());
            }
            if (cache.get("content")!=null) {
                content=cache.get("content").toString();
            }
            if (cache.get("acId")!=null){
                acId=Integer.parseInt(cache.get("acId").toString());
            }
            if (cache.get("uName")!=null){
                uName=cache.get("uName").toString();
            }
            System.out.println(title+"---"+uId+"---"+content+"---"+acId+"---"+uName);
        }
        if (pageView!=null) {
            if (pageView.equals("true")){
                sort="true";
            }
        }
        if (praiseCount!=null) {
            if (praiseCount.equals("true")){
                sort="true";
            }
        }
        if (publishTime!=null){
            if (publishTime.equals("true")){
                sort="true";
            }
        }
        System.out.println(pageView+"=="+praiseCount+"=="+publishTime+"=="+sort);
        Integer pageOff=null;
        Integer pageCount=null;
        pageCount = articleService.queryByConditions(title, uId, content, acId, uName, null, null,pageView,praiseCount,sort).size();
        request.setAttribute("total",pageCount);
        List<Article_Category> list=article_categoryService.allCtgr();
        session.setAttribute("allCtgr",list);
        if(pageCount==0){
            session.setAttribute("article",null);
            return "frontShow/club/club";
        }else {
            pageCount=(pageCount-1)/pageSize+1;
            pageNo=pageNo<1?1:pageNo;
            pageNo=pageNo>pageCount?pageCount:pageNo;
            pageOff=(pageNo-1)*pageSize;
            l=articleService.queryByConditions(title,uId,content,acId,uName,pageOff,pageSize,pageView,praiseCount,sort);
            request.setAttribute("pageNo",pageNo);
            request.setAttribute("article",l);
            request.setAttribute("last",pageCount);
            request.setAttribute("pageOff",pageOff);
            request.setAttribute("size",pageSize);
            return "frontShow/club/club";
        }
    }
}
