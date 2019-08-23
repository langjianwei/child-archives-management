package com.ljw.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @version ：1.0.0
 * @Author: 郎建伟
 * @Modified By:
 * @Description:
 * @Date: Created in 2019/8/19 16:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Child {

    private Integer id;
    private String classes;
    private String name;
    private String sex;
    private Integer age;
    private String birthday;
    private String idCard;
    private String registeredResidence;
    private String height;
    private String weight;
    private String nation;
    private String address;
    private String phone;
    private String admission;
    private String onlyOneChild;
    private String remarks;

}
