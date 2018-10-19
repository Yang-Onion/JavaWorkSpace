package com.yangonion.security.controller;

import com.yangonion.security.model.ImageCode;
import com.yangonion.security.sms.SmsCode;
import com.yangonion.security.util.ImageUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ValidateController {

    public  final static  String SESSSION_IMAGE_CODE_KEY ="SESSSION_IMAGE_CODE_KEY ";
    public  final static  String SESSSION_SMS_CODE_KEY ="SESSSION_SMS_CODE_KEY ";
    private SessionStrategy sessionStrategy= new HttpSessionSessionStrategy();


    @RequestMapping("/code/image")
    public void createCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException,SecurityException {
        ImageCode imageCode= ImageUtil.createImageCode();
        sessionStrategy.setAttribute(new ServletWebRequest(httpServletRequest),SESSSION_IMAGE_CODE_KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",httpServletResponse.getOutputStream());
    }


    @RequestMapping("/code/sms")
    public  void createSmsCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String mobile) throws IOException,SecurityException{
        SmsCode smsCode= new SmsCode(RandomStringUtils.randomNumeric(6),60);
        sessionStrategy.setAttribute(new ServletWebRequest(httpServletRequest), SESSSION_SMS_CODE_KEY + mobile, smsCode);
        System.out.println("您的登录验证码为：" + smsCode.getCode() + "，有效时间为60秒");
    }

}
