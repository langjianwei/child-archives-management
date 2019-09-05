package com.ljw.management.service;

import com.github.qcloudsms.SmsResultBase;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.ljw.management.entity.BirthdayAlert;
import com.ljw.management.entity.Child;
import com.ljw.management.mapper.AlertMapper;
import com.ljw.management.utils.SMSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName AlertService
 * @Description:
 * @Author: 郎建伟
 * @Date: Created in 2019/8/24 1:42
 * @Version: 1.0.0
 */
@Service
public class AlertService {

    private static final Logger logger = LoggerFactory.getLogger(AlertMapper.class);

    @Autowired
    private AlertMapper alertMapper;

    /**
     * @Author: 郎建伟
     * @Description: 发送生日提醒
     * @Date: Created in 2019/8/24 21:43
     * @param: []
     * @return: void
     */
    public void sendBirthdayAlert() {
        // 获取当天日期
        LocalDate localDate = LocalDate.now();
        // 查询所有当天生日的小朋友
        List<BirthdayAlert> birthdayAlertList = alertMapper.getBirthdayAlertList(String.valueOf(localDate));
        /**
         * 生日提醒分三种情况判断
         */
        // 1.当天没有生日的小朋友，不需要发送生日提醒
        if (birthdayAlertList.size() == 0) {
            logger.info("{}当天有:{}位小朋友生日，不需要发送生日提醒！", localDate, birthdayAlertList.size());
            return;
        }
        // 2.当天只有一位小朋友生日
        if (birthdayAlertList.size() == 1) {
            logger.info("{}当天有:{}位小朋友生日！", localDate, birthdayAlertList.size());
            BirthdayAlert alert = birthdayAlertList.get(0);
            String phone = alert.getTeacherPhone();
            String teacher = alert.getTeacher();
            String child = "{" + alert.getName() + "}";
            // 发送短信生日提醒
            SmsSingleSenderResult result = SMSUtil.sendSingleWithTemplate(phone, new String[]{teacher, child});
            // 生日提醒短信发送结果
            sendSmsResult(result, alert);
        }
        // 3.当天多个小朋友生日，老师和小朋友是一对多的关系，合并后只发送一条提醒
        if (birthdayAlertList.size() > 1) {
            // 老师和小朋友关系集合
            Map<String, String> teacherWithChildMap = new HashMap<>();
            // 老师和手机号关系集合
            Map<String, String> teacherWithPhoneMap = new HashMap<>();
            // 老师和班级关系集合
            Map<String, String> teacherWithClassesMap = new HashMap<>();
            for (BirthdayAlert birthdayAlert : birthdayAlertList) {
                // 判断当天生日的小朋友是否是同一个老师
                if (teacherWithChildMap.containsKey(birthdayAlert.getTeacher())) {
                    // 拼接当天生日的所有小朋友名字，只发送一条生日提醒
                    teacherWithChildMap.put(birthdayAlert.getTeacher(), birthdayAlert.getName() + "," + teacherWithChildMap.get(birthdayAlert.getTeacher()));
                } else {
                    teacherWithChildMap.put(birthdayAlert.getTeacher(), birthdayAlert.getName());
                }
                teacherWithPhoneMap.put(birthdayAlert.getTeacher(), birthdayAlert.getTeacherPhone());
                teacherWithClassesMap.put(birthdayAlert.getTeacher(), birthdayAlert.getClasses());
            }
            logger.info("{}当天有:{}位小朋友生日，由于有多个小朋友属于一个班级老师，合并后需要发送的生日提醒为：{}条", localDate, birthdayAlertList.size(), teacherWithChildMap.size());
            // 发送短信生日提醒
            for (Map.Entry<String, String> entry : teacherWithChildMap.entrySet()) {
                String phone = teacherWithPhoneMap.get(entry.getKey());
                String teacher = entry.getKey();
                String child = "{" + entry.getValue() + "}";
                SmsSingleSenderResult result = SMSUtil.sendSingleWithTemplate(phone, new String[]{teacher, child});
                BirthdayAlert birthdayAlert = new BirthdayAlert();
                birthdayAlert.setName(child)
                        .setClasses(teacherWithClassesMap.get(teacher))
                        .setBirthday(String.valueOf(localDate))
                        .setTeacher(teacher)
                        .setTeacherPhone(phone);
                // 生日提醒短信发送结果
                sendSmsResult(result, birthdayAlert);
                // 发送完一次，休息35秒，因为腾讯的发送频率限制
                try {
                    Thread.sleep(35000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @Author: 郎建伟
     * @Description: 生日提醒短信发送结果
     * @Date: Created in 2019/8/24 21:39
     * @param: [result, birthdayAlert]
     * @return: void
     */
    private void sendSmsResult(SmsSingleSenderResult result, BirthdayAlert birthdayAlert) {
        // 短信提醒是否发送成功
        if (result.result == 0 && result.errMsg.equals("OK")) {
            // 发送短信提醒记录持久化
            addBirthdayAlert(birthdayAlert);
            logger.info("短信提醒发送成功：{}", result);
            return;
        }
        logger.info("短信提醒发送失败：{}", result);
    }

    /**
     * @Author: 郎建伟
     * @Description: 添加生日短信提醒
     * @Date: Created in 2019/8/24 21:38
     * @param: [birthdayAlert]
     * @return: void
     */
    private void addBirthdayAlert(BirthdayAlert birthdayAlert) {
        alertMapper.addBirthdayAlert(birthdayAlert);
    }

//    public static void main(String[] args) {
//
//        System.out.println(localDate);
//    }

}
