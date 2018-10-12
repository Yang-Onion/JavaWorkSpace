package com.yangonion.email.controller;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${emailReceiver}")
    private String to;

    @Autowired
    private  TemplateEngine templateEngine;

    private  String info="发送邮件成功！";

    @RequestMapping(value = "/sendSimpleEmail")
    public String sendSimpleEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject("邮件标题");
            message.setText("邮件正文");
            javaMailSender.send(message);
            return info;
        } catch (MailException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/sendEmailWithStyle")
    public String sendEmailWithStyle(){
        try {
            MimeMessage message =null;
            message=javaMailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setSubject("带HTML格式的邮件");
            helper.setTo(to);

            StringBuffer sb = new StringBuffer("<p style='color:lightblue'>访问github请点击<a href='https://www.github.com'>前往</a></p>");
            helper.setText(sb.toString(),true);
            javaMailSender.send(message);
            return info;
        } catch (MessagingException e) {
            e.printStackTrace();
            return  e.getMessage();
        }
    }


    @RequestMapping("/sendEmailWithAttach")
    public  String sendEmailWithAttach(){
        MimeMessage message= null;
        try {
            message=javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setSubject("带附件的邮件");
            helper.setFrom(from);
            helper.setTo(to);
            helper.setText("合同,详见附件");

            FileSystemResource attach = new FileSystemResource(new File("src/main/resources/static/合同.docx"));
            helper.addAttachment("合同.docx",attach);

            //正文包括图片
            /*
            helper.setText("<html><body>测试正文包括内容：<img src='cid:img'/></body></html>", true);
            // 传入附件
            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/30.jpg"));
            helper.addInline("img", file);
            */
            javaMailSender.send(message);
            return info;
        } catch (MessagingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    @RequestMapping("/sendEmailWithTemplate")
    public  String sendEmailWithTemplate(String code){
        MimeMessage message= null;
        try {
            message=javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setSubject("模板邮件");
            helper.setFrom(from);
            helper.setTo(to);

            Context context = new Context();
            context.setVariable("code",code);
            String template = templateEngine.process("emailTemplate",context);
            helper.setText(template,true);

            javaMailSender.send(message);
            return info;
        } catch (MessagingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
