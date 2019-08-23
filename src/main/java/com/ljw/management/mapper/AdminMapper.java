package com.ljw.management.mapper;

import com.ljw.management.entity.Child;
import com.ljw.management.entity.Classes;
import org.apache.ibatis.annotations.Mapper;

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

    List<Classes> getClassesList();
}
