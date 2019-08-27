package com.ljw.management.mapper;

import com.ljw.management.entity.BirthdayAlert;
import com.ljw.management.entity.Child;
import com.ljw.management.entity.Classes;
import com.ljw.management.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version ：1.0.0
 * @Author: 郎建伟
 * @Modified By:
 * @Description:
 * @Date: Created in 2019/8/16 13:47
 */
@Mapper
public interface AdminMapper {

    /**
     * @Author: 郎建伟
     * @Description: 根据条件查询幼儿档案信息列表
     * @Date: Created in 2019/8/19 17:22
     * @param: [child]
     * @return: java.util.List<com.ljw.management.entity.Child>
     */
    List<Child> getChildList(Child child);

    /**
     * @Author: 郎建伟
     * @Description: 添加幼儿档案信息
     * @Date: Created in 2019/8/20 17:22
     * @param: [child]
     * @return: java.util.List<com.ljw.management.entity.Child>
     */
    void addChild(Child child);

    /**
     * @Author: 郎建伟
     * @Description: 修改幼儿档案信息
     * @Date: Created in 2019/8/20 17:22
     * @param: [child]
     * @return: java.util.List<com.ljw.management.entity.Child>
     */
    void updateChild(Child child);
    /**
     * @Author: 郎建伟
     * @Description: 删除幼儿档案信息
     * @Date: Created in 2019/8/21 21:22
     * @param: [childId]
     * @return: java.util.List<com.ljw.management.entity.Child>
     */
    void deleteChild(Integer childId);

    /**
     * @Author: 郎建伟
     * @Description: 查询用户列表
     * @Date: Created in 2019/8/19 17:22
     * @param: [child]
     * @return: java.util.List<com.ljw.management.entity.User>
     */
    List<User> getUserList(User user);

    /**
     * @Author: 郎建伟
     * @Description: 添加用户
     * @Date: Created in 2019/8/20 17:22
     * @param: [child]
     * @return: java.util.List<com.ljw.management.entity.User>
     */
    void addUser(User user);

    /**
     * @Author: 郎建伟
     * @Description: 修改用户
     * @Date: Created in 2019/8/20 17:22
     * @param: [child]
     * @return: java.util.List<com.ljw.management.entity.User>
     */
    void updateUser(User user);
    /**
     * @Author: 郎建伟
     * @Description: 删除用户
     * @Date: Created in 2019/8/21 21:22
     * @param: [childId]
     * @return: java.util.List<com.ljw.management.entity.User>
     */
    void deleteUser(Integer id);

    List<Classes> getClassesList(Classes classes);

    void addClasses(Classes classes);

    void updateClasses(Classes classes);

    void deleteClasses(Integer id);

    List<BirthdayAlert> getBirthdayAlertList(@Param("classes") Object classes);

}
