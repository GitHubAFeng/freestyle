package com.afeng.web.common.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * 邮件发送，最好使用异步发送
 **/
@Service
@Slf4j
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * mailService.sendSimpleMail("", "简单的信息", "测试数据");
     */
    public void sendSimpleMail(String to, String subject, String content) {
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发的
        message.setFrom(from);
        //谁要接收
        message.setTo(to);
        //邮件标题
        message.setSubject(subject);
        //邮件内容
        message.setText(content);
        try {
            javaMailSender.send(message);
            log.debug("简单邮件已经发送了");
        } catch (Exception e) {
            log.error("简单邮件发送出现了异常，发送失败，{}", e.getMessage());
        }
    }

    /**
     * mailService.sendHtmlMail("", "SpringBootHTML邮件测试","<html><body><h1>邮件标题</h1><font color='blue'>Hello</font></body><html>");
     */
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(message);
            log.debug("HTML邮件已经发送了");
        } catch (Exception e) {
            log.error("HTML邮件发送出现了异常，发送失败");
        }
    }

    /**
     * mailService.sendMailWithFile("", "SpringBoot附件邮件测试","文件邮件","E:\\temp\\2.txt");
     */
    @SuppressWarnings("all")
    public void sendMailWithFile(String to, String subject, String content, String filepath) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource file = new FileSystemResource(new File(filepath));
            helper.addAttachment(file.getFilename(), file);
            javaMailSender.send(message);
            log.debug("文件邮件已经发送了");
        } catch (Exception e) {
            log.error("文件邮件发送出现了异常，发送失败", e);
        }
    }

    /**
     * String imgId="2";
     * String content="<html><body>这是有图片的邮件：<img src=\'cid:"+imgId + "\'></body></html>";
     * mailService.sendMailWithImg("", "SpringBoot图片邮件测试",
     * content,"E:\\temp\\10.jpg",imgId);
     */
    public void sendMailWithImg(String to, String subject, String content, String imgPath, String imgId) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource file = new FileSystemResource(new File(imgPath));
            helper.addInline(imgId, file);
            javaMailSender.send(message);
            log.debug("图片邮件已经发送了");
        } catch (Exception e) {
            log.error("图片邮件发送出现了异常，发送失败，原因是：{}", e.getMessage());
        }
    }

}
