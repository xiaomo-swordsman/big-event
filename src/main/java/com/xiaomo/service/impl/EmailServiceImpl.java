package com.xiaomo.service.impl;

import com.xiaomo.pojo.EmailProperties;
import com.xiaomo.service.EmailService;
import com.xiaomo.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailProperties emailProperties;

    /*
     *  to:收件人邮箱
     *  title:邮件标题
     *  conten: 邮件内容
     */
    @Override
    public boolean sendEmail(String to, String title, String content){
        System.out.println("emailProperties == " + emailProperties);

        boolean flag = EmailUtil.sendEmail(emailProperties,to,title,content);

        return flag;
    }
}
