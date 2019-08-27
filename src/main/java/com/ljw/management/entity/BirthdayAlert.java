package com.ljw.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName BirthdayAlert
 * @Description: 生日提醒
 * @Author: 郎建伟
 * @Date: Created in 2019/8/24 1:57
 * @Version: 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BirthdayAlert {

    private String name;
    private String classes;
    private String birthday;
    private String teacher;
    private String teacherPhone;

}
