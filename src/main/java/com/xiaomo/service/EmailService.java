package com.xiaomo.service;

public interface EmailService {
    public boolean sendEmail(String to, String title, String content);
}
