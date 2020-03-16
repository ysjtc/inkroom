package com.mx.interCeptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.utils.Anno.PreventRepeat;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class RepeatInterCeptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        // 1. 判断handler参数是否为HandlerMethod类的实例
        if (handler instanceof HandlerMethod) {

            // 2. 获取方法注解查看方式是否有PreventRepeat注解
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            PreventRepeat annotation = method.getAnnotation(PreventRepeat.class);

            if (annotation != null) {

                // 3. 调用重复数据验证方法
                boolean result = repeatValidator(httpServletRequest);
                if(result){
                    return false;
                }
                else{
                    return true;
                }
            }else{
                return true;
            }
        } else {

            // 4. 如果参数不是HandlerMethod类的实例则调用父类的preHandle方法
            return super.preHandle(httpServletRequest, httpServletResponse, handler);
        }
    }

    /*
     * @Author 郭子雄
     * @Description //验证一个url是否重复提交
     * @Date 20:26 2019/7/17
     * @Param [httpServletRequest]
     * @return boolean
     **/
    public boolean repeatValidator(HttpServletRequest httpServletRequest) throws Exception {

            // 1. 将请求参数转换为json字符串 需要在pom内引用jackson-databind
            ObjectMapper objectMapper = new ObjectMapper();
            String params = objectMapper.writeValueAsString(httpServletRequest.getParameterMap());

            // 2. 获取当前请求的url地址 并以url为key 参数为值存在map内
            String url = httpServletRequest.getRequestURI();
            Map<String, String> map = new HashMap(4);
            map.put(url, params);
            String nowUrlParams = map.toString();

            // 3. 获取session中上一次请求存储的url和参数字符串
            Object preUrlParams = httpServletRequest.getSession().getAttribute("oldUrlParams");

            // 4. 如果上一个数据为null,表示还没有访问页面 将当前方位的url和请求参数存储到session中
            if (preUrlParams == null) {
                httpServletRequest.getSession().setAttribute("oldUrlParams", nowUrlParams);
                return false;
            } else {

                // 5. 判断上一次访问的url和参数与本次是否相同 如相同则表示重复数据
                if (preUrlParams.toString().equals(nowUrlParams)) {
                    return true;
                } else {
                    httpServletRequest.getSession().setAttribute("oldUrlParams", nowUrlParams);
                    return false;
                }

            }
        }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
