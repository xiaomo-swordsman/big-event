package com.xiaomo.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "email")
public class EmailProperties {
    // 发件人邮箱
    //@Value("${email.user}")
    public String user;

    // 发件人邮箱授权码
    public String code;

    // 发件人邮箱对应的邮箱服务器域名，如果是163邮箱：smtp.163.com  qq邮箱：smtp.qq.com
    public String host;

    // 邮箱端口号
    private String port;

    // 启用ssl
    private boolean sslEnable;

    // 身份验证开关
    public boolean auth;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public boolean isSslEnable() {
        return sslEnable;
    }

    public void setSslEnable(boolean sslEnable) {
        this.sslEnable = sslEnable;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    @Override
    public String toString() {
        return "EmailProperties{" +
                "user='" + user + '\'' +
                ", code='" + code + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", sslEnable=" + sslEnable +
                ", auth=" + auth +
                '}';
    }
}
