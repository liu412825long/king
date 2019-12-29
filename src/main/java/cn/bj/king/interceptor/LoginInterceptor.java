package cn.bj.king.interceptor;

import cn.bj.king.util.JSON;
import cn.bj.king.util.RedisUtil;
import com.alipay.api.domain.Account;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {       //请求进入这个拦截器
        HttpSession session = request.getSession();
        String authToken=request.getHeader("X-Auth-Token");
        //判断该token是否失效
        if(StringUtils.isEmpty(authToken)){
            response.sendRedirect("/");
            return false;
        }
        Object accountStr=redisUtil.get(authToken);
        if(accountStr==null){
            response.sendRedirect("/");
            return false;
        }
        String accountJson=accountStr.toString();
        if(StringUtils.isEmpty(accountJson)){
            response.sendRedirect("/");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
