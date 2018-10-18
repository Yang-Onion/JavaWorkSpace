package com.yangonion.security.controller;

import com.yangonion.security.model.ImageCode;
import com.yangonion.security.util.ImageUtil;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.ref.PhantomReference;

@RestController
public class ValidateController {

    public  final static  String SESSSION_IMAGE_CODE_KEY ="SESSSION_IMAGE_CODE_KEY ";
    private SessionStrategy sessionStrategy= new HttpSessionSessionStrategy();


    @RequestMapping("/code/image")
    public void createCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException,SecurityException {
        ImageCode imageCode= ImageUtil.createImageCode();
        sessionStrategy.setAttribute(new ServletWebRequest(httpServletRequest),SESSSION_IMAGE_CODE_KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",httpServletResponse.getOutputStream());
    }

}
