package com.xiaomo.util;

import com.xiaomo.pojo.EmailProperties;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class EmailUtil {
    public static boolean sendEmail(EmailProperties emaiProperties, String to, String title, String content){
        // 设置邮件服务器属性
        Properties properties = System.getProperties();
        // 通用配置
        properties.setProperty("mail.smtp.host", emaiProperties.getHost()); // 主机名
        properties.setProperty("mail.smtp.port", emaiProperties.getPort()); // 端口号
        properties.setProperty("mail.smtp.auth", emaiProperties.isAuth() + ""); // 身份验证开关，开启
        properties.setProperty("mail.transport.protocol", "smtp");

        // 163 邮箱配置
        // properties.setProperty("mail.smtp.ssl.enable", emaiProperties.isSslEnable() + ""); // 是否启用ssl链接，开启

        // Microsoft365 邮箱配置
        properties.setProperty("mail.smtp.starttls.enable", emaiProperties.isSslEnable() + ""); // 是否启用ssl链接，开启
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        // 获取默认的邮件会话
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emaiProperties.getUser(), emaiProperties.getCode());
            }
        });

        try {
            // 创建一个默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // 设置发件人
            message.setFrom(new InternetAddress(emaiProperties.getUser()));

            // 设置收件人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // 设置主题
            message.setSubject(title);

            // 设置邮件正文
            message.setText(content);

            // 发送邮件
            Transport.send(message);
            System.out.println("邮件发送成功！");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return true;
    }
}
