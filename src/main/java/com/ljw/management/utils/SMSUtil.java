package com.ljw.management.utils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version ：1.0.0
 * @Author: 郎建伟
 * @Modified By:
 * @Description: 发送短信
 * @Date: Created in 2019/8/23 10:36
 */
public class SMSUtil {

    private final static Logger logger = LoggerFactory.getLogger(SMSUtil.class);

    private final static int appId = 1400243138;
    private final static String appKey = "8440bf7f6f84e4090ef8c7509c320f2f";
    private final static String smsSign = "郎建伟的学习笔记";
    private final static int templateId = 402713;

    public static SmsSingleSenderResult sendWithTemplate(String phone, String[] params) {
        SmsSingleSender sender = new SmsSingleSender(appId, appKey);
        try {
            SmsSingleSenderResult result = sender
                    .sendWithParam("86", phone, templateId, params, smsSign, "", "");

            return result;
        } catch (Exception e) {
            // HTTP 响应码错误
            e.printStackTrace();
            logger.error("发送短信异常：{},请检查！", e.getMessage());
        }

        return null;
    }

    public static void main(String[] args) {
        SMSUtil.sendWithTemplate("17600604700", new String[]{"郎建伟"});
    }

}
