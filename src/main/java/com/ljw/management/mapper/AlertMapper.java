package com.ljw.management.mapper;

import com.ljw.management.entity.BirthdayAlert;
import com.ljw.management.entity.Child;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AlertMapper {

    List<BirthdayAlert> getBirthdayAlertList(@Param("birthday") String birthday);
    void addBirthdayAlert(BirthdayAlert birthdayAlert);
}
