package com.ljw.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName User
 * @Description:
 * @Author: 郎建伟
 * @Date: Created in 2019/8/24 23:39
 * @Version: 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {

    private Integer id;
    private String username;
    private String password;
    private String classes;

}
