package com.ljw.management.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @version ：1.0.0
 * @Author: 郎建伟
 * @Modified By:
 * @Description: 全局错误处理
 * @Date: Created in 2019/9/05 17:54
 */
@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == 404) {
            return "404";
        } else {
            return "500";
        }
    }
    @Override
    public String getErrorPath() {
        return "/error";
    }

}
