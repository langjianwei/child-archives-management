package com.ljw.management.controller;

import com.ljw.management.entity.Child;
import com.ljw.management.entity.Classes;
import com.ljw.management.entity.JsonResult;
import com.ljw.management.entity.User;
import com.ljw.management.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @version ：1.0.0
 * @Author: 郎建伟
 * @Modified By:
 * @Description: 前端基础功能总控制类
 * @Date: Created in 2019/8/16 13:46
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * @Author: 郎建伟
     * @Description: 测试
     * @Date: Created in 2019/8/16 15:55
     * @param: []
     * @return: java.lang.String
     */
    @RequestMapping("/hello")
    public String hello() {
        return "hello,郎建伟！";
    }


    /**
     * @Author: 郎建伟
     * @Description: 登录
     * @Date: Created in 2019/8/16 15:55
     * @param: []
     * @return: java.lang.String
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * @Author: 郎建伟
     * @Description: 登录
     * @Date: Created in 2019/8/16 15:55
     * @param: []
     * @return: java.lang.String
     */
    @RequestMapping("/logOut")
    @ResponseBody
    public JsonResult loginOut() {
        return adminService.logOut();
    }

    /**
     * @Author: 郎建伟
     * @Description: 登录验证用户名密码
     * @Date: Created in 2019/8/16 16:19
     * @param: [httpRequest, username, password]
     * @return: com.ljw.management.entity.JsonResult
     */
    @RequestMapping("/loginValid")
    @ResponseBody
    public JsonResult loginValid(HttpServletRequest httpRequest,
                                 String username,
                                 String password) {
        return adminService.loginValid(httpRequest, username, password);
    }

    /**
     * @Author: 郎建伟
     * @Description: 登录验证成功，打开首页
     * @Date: Created in 2019/8/16 16:20
     * @param: []
     * @return: java.lang.String
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * @Author: 郎建伟
     * @Description: 打开幼儿个人信息页面
     * @Date: Created in 2019/8/16 18:14
     * @param: []
     * @return: java.lang.String
     */
    @RequestMapping("/getChildInfo")
    public String childInfo(ModelMap modelMap, Child child) {
        return adminService.getChildInfo(modelMap, child);
    }

    /**
     * @Author: 郎建伟
     * @Description: 添加幼儿档案信息
     * @Date: Created in 2019/8/20 15:54
     * @param: [child, type]
     * @return: com.ljw.management.entity.JsonResult
     */
    @RequestMapping("/addChildInfo")
    @ResponseBody
    public JsonResult addChildInfo(Child child, String type) {

        return adminService.addChildInfo(child, type);
    }

    /**
     * @Author: 郎建伟
     * @Description: 更新幼儿档案信息
     * @Date: Created in 2019/8/20 17:54
     * @param: [child, type]
     * @return: com.ljw.management.entity.JsonResult
     */
    @RequestMapping("/updateChildInfo")
    @ResponseBody
    public JsonResult updateChildInfo(Child child, String type) {

        return adminService.updateChildInfo(child, type);
    }
    /**
     * @Author: 郎建伟
     * @Description: 删除幼儿档案信息
     * @Date: Created in 2019/8/21 17:54
     * @param: [child, type]
     * @return: com.ljw.management.entity.JsonResult
     */
    @RequestMapping("/deleteChildInfo")
    @ResponseBody
    public JsonResult deleteChildInfo(Integer childId, String name, String type) {
        return adminService.deleteChildInfo(childId, name, type);
    }

    /**
     * @Author: 郎建伟
     * @Description: 查询班级信息
     * @Date: Created in 2019/8/16 18:14
     * @param: []
     * @return: java.lang.String
     */
    @RequestMapping("/getClasses")
    public String getClasses(ModelMap modelMap) {
        return adminService.getClasses(modelMap);
    }
    /**
     * @Author: 郎建伟
     * @Description: 查询用户信息
     * @Date: Created in 2019/8/16 18:14
     * @param: []
     * @return: java.lang.String
     */
    @RequestMapping("/getUser")
    public String getUser(ModelMap modelMap, User user) {
        return adminService.getUser(modelMap, user);
    }

    /**
     * @Author: 郎建伟
     * @Description: 添加用户信息
     * @Date: Created in 2019/8/20 15:54
     * @param: [user, type]
     * @return: com.ljw.management.entity.JsonResult
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public JsonResult addUser(User user, String type) {

        return adminService.addUser(user, type);
    }
    /**
     * @Author: 郎建伟
     * @Description: 添加班级信息
     * @Date: Created in 2019/8/20 15:54
     * @param: [classes, type]
     * @return: com.ljw.management.entity.JsonResult
     */
    @RequestMapping("/addClasses")
    @ResponseBody
    public JsonResult addClasses(Classes classes, String type) {

        return adminService.addClasses(classes, type);
    }

    /**
     * @Author: 郎建伟
     * @Description: 更新用户信息
     * @Date: Created in 2019/8/20 17:54
     * @param: [user, type]
     * @return: com.ljw.management.entity.JsonResult
     */
    @RequestMapping("/updateUser")
    @ResponseBody
    public JsonResult updateUser(User user, String type) {

        return adminService.updateUser(user, type);
    }
    /**
     * @Author: 郎建伟
     * @Description: 更新班级信息
     * @Date: Created in 2019/8/20 17:54
     * @param: [classes, type]
     * @return: com.ljw.management.entity.JsonResult
     */
    @RequestMapping("/updateClasses")
    @ResponseBody
    public JsonResult updateClasses(Classes classes, String type) {

        return adminService.updateClasses(classes, type);
    }
    /**
     * @Author: 郎建伟
     * @Description: 删除用户信息
     * @Date: Created in 2019/8/21 17:54
     * @param: [id, username, type]
     * @return: com.ljw.management.entity.JsonResult
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public JsonResult deleteUser(Integer id, String username, String type) {
        return adminService.deleteUser(id, username, type);
    }
    /**
     * @Author: 郎建伟
     * @Description: 删除班级信息
     * @Date: Created in 2019/8/21 17:54
     * @param: [id, username, type]
     * @return: com.ljw.management.entity.JsonResult
     */
    @RequestMapping("/deleteClasses")
    @ResponseBody
    public JsonResult deleteClasses(Integer id, String username, String type) {
        return adminService.deleteClasses(id, username, type);
    }

    /**
     * @Author: 郎建伟
     * @Description: 打开生日提醒页面
     * @Date: Created in 2019/8/16 18:20
     * @param: []
     * @return: java.lang.String
     */
    @RequestMapping("/getBirthdayAlert")
    public String birthdayAlert(ModelMap modelMap) {
        return adminService.getBirthdayAlert(modelMap);
    }


    // 打开500
    @RequestMapping("/500")
    public String error500() {
        return "500";
    }

    // 打开404
    @RequestMapping("/404")
    public String error404() {
        return "404";
    }

}
