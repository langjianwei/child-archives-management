package com.ljw.management.service;

import com.ljw.management.entity.*;
import com.ljw.management.mapper.AdminMapper;
import com.ljw.management.utils.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    /**
     * @Author: 郎建伟
     * @Description: 登录验证
     * @Date: Created in 2019/8/24 21:45
     * @param: [httpRequest, username, password]
     * @return: com.ljw.management.entity.JsonResult
     */
    public JsonResult loginValid(HttpServletRequest httpRequest,
                                 String username,
                                 String password) {

        String requestIp = IPUtil.getRealIp(httpRequest);
        User user = new User();
        user.setUsername(username).setPassword(password);
        List<User> userList = adminMapper.getUserList(user);
        if (userList.size() > 0) {
            session = httpRequest.getSession();
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            session.setAttribute("classes", userList.get(0).getClasses());
            session.setAttribute("address", requestIp);
            session.setAttribute("loginTime", LocalDateTime.now());
            logger.info("登录成功，当前登录地点：{}，用户：{}", requestIp, username);
            return new JsonResult(200, "登录成功","");
        }
        return new JsonResult(500, "登录失败","请检查用户名和密码");
    }

    /**
     * @Author: 郎建伟
     * @Description: 退出登录
     * @Date: Created in 2019/8/24 22:45
     * @param: [httpRequest, httpResponse]
     * @return: com.ljw.management.entity.JsonResult
     */
    public JsonResult logOut() {
        logger.info("退出登录，销毁session信息：{}", session.getId());
        session.invalidate();
        return new JsonResult(200, "注销登录成功", "");
    }

    /**
     * @Author: 郎建伟
     * @Description: 查询幼儿档案信息
     * @Date: Created in 2019/8/24 21:47
     * @param: [modelMap, child]
     * @return: java.lang.String
     */
    public String getChildInfo(ModelMap modelMap, Child child) {
        if (!"admin".equals(session.getAttribute("username"))) {
            child.setClasses((String) session.getAttribute("classes"));
        }
        List<Child> childList = adminMapper.getChildList(child);
        List<Classes> classesList = adminMapper.getClassesList(null);
        modelMap.addAttribute("childInfoList", childList);
        modelMap.addAttribute("classesList", classesList);
        return "childInfo";
    }

    /**
     * @Author: 郎建伟
     * @Description: 添加幼儿档案信息
     * @Date: Created in 2019/8/24 21:47
     * @param: [child, type]
     * @return: com.ljw.management.entity.JsonResult
     */
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

    /**
     * @Author: 郎建伟
     * @Description: 更新幼儿档案信息
     * @Date: Created in 2019/8/24 21:47
     * @param: [child, type]
     * @return: com.ljw.management.entity.JsonResult
     */
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

    /**
     * @Author: 郎建伟
     * @Description: 删除幼儿档案信息
     * @Date: Created in 2019/8/24 21:48
     * @param: [childId, name, type]
     * @return: com.ljw.management.entity.JsonResult
     */
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

    /**
     * @Author: 郎建伟
     * @Description: 查询生日提醒记录
     * @Date: Created in 2019/8/24 21:48
     * @param: [modelMap]
     * @return: java.lang.String
     */
    public String getBirthdayAlert(ModelMap modelMap) {
        List<BirthdayAlert> birthdayAlertList;
        if (!"admin".equals(session.getAttribute("username"))) {
            birthdayAlertList = adminMapper.getBirthdayAlertList(session.getAttribute("classes"));
        } else {
            birthdayAlertList = adminMapper.getBirthdayAlertList(null);
        }
        modelMap.addAttribute("birthdayAlertList", birthdayAlertList);
        return "birthdayAlert";
    }

    /**
     * @Author: 郎建伟
     * @Description: 查询用户信息
     * @Date: Created in 2019/8/24 21:47
     * @param: [modelMap, user]
     * @return: java.lang.String
     */
    public String getUser(ModelMap modelMap, User user) {
        List<User> userList = adminMapper.getUserList(user);
        List<Classes> classesList = adminMapper.getClassesList(null);
        modelMap.addAttribute("classesList", classesList);
        modelMap.addAttribute("userList", userList);
        return "userManage";
    }
    /**
     * @Author: 郎建伟
     * @Description: 查询班级信息
     * @Date: Created in 2019/8/24 21:47
     * @param: [modelMap, user]
     * @return: java.lang.String
     */
    public String getClasses(ModelMap modelMap) {
        List<Classes> classesList = adminMapper.getClassesList(null);
        modelMap.addAttribute("classesList", classesList);
        return "classesManage";
    }

    /**
     * @Author: 郎建伟
     * @Description: 添加用户信息
     * @Date: Created in 2019/8/24 21:47
     * @param: [user, type]
     * @return: com.ljw.management.entity.JsonResult
     */
    public JsonResult addUser(User user, String type) {
        // 查询用户是否存在
        user.setPassword(null);
        List<User> userList = adminMapper.getUserList(user);
        if (userList.size() > 0) {
            logger.info("用户信息" + type + "失败, 该用户已存在");
            return new JsonResult(500, type + "失败","该用户已存在,请修改用户名");
        }
        try {
            adminMapper.addUser(user);
        } catch (Exception e) {
            logger.error("用户信息" + type + "失败：{}", e.getMessage());
            return new JsonResult(500, type + "失败",e.getMessage());
        }
        logger.info("用户信息：{}，【{}】成功", user, type);
        return new JsonResult(200, type + "成功","");
    }

    /**
     * @Author: 郎建伟
     * @Description: 添加班级信息
     * @Date: Created in 2019/8/24 21:47
     * @param: [user, type]
     * @return: com.ljw.management.entity.JsonResult
     */
    public JsonResult addClasses(Classes classes, String type) {
        // 查询班级是否存在
        List<Classes> classesList = adminMapper.getClassesList(classes);
        if (classesList.size() > 0) {
            logger.info("班级信息" + type + "失败, 该用户已存在");
            return new JsonResult(500, type + "失败","该班级已存在,请勿重复添加");
        }
        try {
            adminMapper.addClasses(classes);
        } catch (Exception e) {
            logger.error("班级信息" + type + "失败：{}", e.getMessage());
            return new JsonResult(500, type + "失败",e.getMessage());
        }
        logger.info("班级信息：{}，【{}】成功", classes, type);
        return new JsonResult(200, type + "成功","");
    }

    /**
     * @Author: 郎建伟
     * @Description: 更新用户信息
     * @Date: Created in 2019/8/24 21:47
     * @param: [user, type]
     * @return: com.ljw.management.entity.JsonResult
     */
    public JsonResult updateUser(User user, String type) {
        try {
            adminMapper.updateUser(user);
        } catch (Exception e) {
            logger.error("用户信息" + type + "失败：{}", e.getMessage());
            return new JsonResult(500, type + "失败",e.getMessage());
        }
        logger.info("用户信息：{}，【{}】成功", user, type);
        return new JsonResult(200, type + "成功","");
    }

    /**
     * @Author: 郎建伟
     * @Description: 更新班级信息
     * @Date: Created in 2019/8/24 21:47
     * @param: [user, type]
     * @return: com.ljw.management.entity.JsonResult
     */
    public JsonResult updateClasses(Classes classes, String type) {
        try {
            adminMapper.updateClasses(classes);
        } catch (Exception e) {
            logger.error("班级信息" + type + "失败：{}", e.getMessage());
            return new JsonResult(500, type + "失败",e.getMessage());
        }
        logger.info("班级信息：{}，【{}】成功", classes, type);
        return new JsonResult(200, type + "成功","");
    }

    /**
     * @Author: 郎建伟
     * @Description: 删除用户信息
     * @Date: Created in 2019/8/24 21:48
     * @param: [id, name, type]
     * @return: com.ljw.management.entity.JsonResult
     */
    public JsonResult deleteUser(Integer id, String username, String type) {
        try {
            adminMapper.deleteUser(id);
        } catch (Exception e) {
            logger.error("用户信息" + type + "失败：{}", e.getMessage());
            return new JsonResult(500, type + "失败",e.getMessage());
        }

        logger.info("用户信息：{}，【{}】成功", username, type);
        return new JsonResult(200, type + "成功","");
    }

    /**
     * @Author: 郎建伟
     * @Description: 删除班级信息
     * @Date: Created in 2019/8/24 21:48
     * @param: [id, name, type]
     * @return: com.ljw.management.entity.JsonResult
     */
    public JsonResult deleteClasses(Integer id, String name, String type) {
        try {
            adminMapper.deleteClasses(id);
        } catch (Exception e) {
            logger.error("班级信息" + type + "失败：{}", e.getMessage());
            return new JsonResult(500, type + "失败",e.getMessage());
        }

        logger.info("用户信息：{}，【{}】成功", name, type);
        return new JsonResult(200, type + "成功","");
    }
}
