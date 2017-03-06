package com.yui.interceptor;

import com.yui.exception.AuthorizationException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */
public class LoginInterceptor implements HandlerInterceptor {
    private List<String> excludedUrl;

    public List<String> getExcludedUrl() {
        return excludedUrl;
    }

    public void setExcludedUrl(List<String> excludedUrl) {
        this.excludedUrl = excludedUrl;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getRequestURI();
        for(String url : excludedUrl){
            if(requestUrl.endsWith(url) || requestUrl.matches(url)){
                return true;
            }
        }

        if(requestUrl.equals("/")){
            return true;
        }

        Object user = request.getSession().getAttribute("user");
        if(user == null){
            throw new AuthorizationException();
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
