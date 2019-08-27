package com.ljw.management.controller;

import com.ljw.management.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName AlertController
 * @Description:
 * @Author: 郎建伟
 * @Date: Created in 2019/8/24 1:41
 * @Version: 1.0.0
 */
@Component
public class AlertController {

    @Autowired
    private AlertService alertService;

    @Scheduled(cron = "0 0 8,13 * * ?")
//    @Scheduled(cron = "0 */3 * * * ?")
    public void sendBirthdayAlert() {
        alertService.sendBirthdayAlert();
    }
}
