package com.ljw.management.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginInterceptor
 * @Description:
 * @Author: 郎建伟
 * @Date: Created in 2019/8/25 12:45
 * @Version: 1.0.0
 */
public class LoginInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @Author: 郎建伟
     * @Description: 检查用户是否登录或登录是否过期
     *               preHandle在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
     * @Date: Created in 2019/6/12 10:43
     * @param: [request, response, handler]
     * @return: boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        logger.info("用户请求的路径为：{}", request.getRequestURI());

        if (null == request.getSession().getAttribute("username")) {
            logger.info("用户未登录或登录过期，请重新登录...");
            response.sendRedirect("/admin/login");
            return false;
        }

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("afterCompletion，资源调用结束，开始清理占用资源...");
    }
}
