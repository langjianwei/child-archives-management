package com.ljw.management.service;

import com.ljw.management.entity.Child;
import com.ljw.management.entity.Classes;
import com.ljw.management.entity.JsonResult;
import com.ljw.management.mapper.AdminMapper;
import com.ljw.management.utils.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @version ：1.0.0
 * @Author: 郎建伟
 * @Modified By:
 * @Description:
 * @Date: Created in 2019/8/16 13:46
 */
@Service
public class AdminService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    HttpSession session;

    // 登录验证用户名密码
    public JsonResult loginValid(HttpServletRequest httpRequest,
                                 String username,
                                 String password) {

        String requestIp = IPUtil.getRealIp(httpRequest);
        if ("admin".equals(username) && "admin".equals(password)) {
            session = httpRequest.getSession();
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            session.setAttribute("address", requestIp);
            session.setAttribute("loginTime", LocalDateTime.now());
            logger.info("登录成功，当前登录地点：{}，用户：{}", requestIp, username);
            return new JsonResult(200, "登录成功","");

        }
        return new JsonResult(500, "登录失败","请检查用户名和密码");
    }

    public String clildInfo(ModelMap modelMap, Child child) {
        List<Child> childList = adminMapper.getChildList(child);
        List<Classes> classesList = adminMapper.getClassesList();
        modelMap.addAttribute("childInfoList", childList);
        modelMap.addAttribute("classesList", classesList);
        return "childInfo";
    }

    public JsonResult addChildInfo(Child child, String type) {

        try {
            adminMapper.addChild(child);
        } catch (Exception e) {
            logger.error("幼儿档案" + type + "失败：{}", e.getMessage());
            return new JsonResult(500, type + "失败",e.getMessage());
        }
        logger.info("幼儿档案：{}，【{}】成功", child, type);
        return new JsonResult(200, type + "成功","");
    }

    public JsonResult updateChildInfo(Child child, String type) {

        try {
            adminMapper.updateChild(child);
        } catch (Exception e) {
            logger.error("幼儿档案" + type + "失败：{}", e.getMessage());
            return new JsonResult(500, type + "失败",e.getMessage());
        }
        logger.info("幼儿档案：{}，【{}】成功", child, type);
        return new JsonResult(200, type + "成功","");
    }

    public JsonResult deleteChildInfo(Integer childId, String name, String type) {
        try {
            adminMapper.deleteChild(childId);
        } catch (Exception e) {
            logger.error("幼儿档案" + type + "失败：{}", e.getMessage());
            return new JsonResult(500, type + "失败",e.getMessage());
        }

        logger.info("幼儿档案：{}，【{}】成功", name, type);
        return new JsonResult(200, type + "成功","");
    }
}
