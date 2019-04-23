package cn.edu.dlnu.question.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin(maxAge = 3600)
@RequestMapping("/kaptcha")
public class KaptchaController {

  /**
   * 1、验证码工具
   */
  @Autowired
  DefaultKaptcha defaultKaptcha;

  /**
   * 2、生成验证码
   * @param httpServletRequest
   * @param httpServletResponse
   * @throws Exception
   */
  @RequestMapping("/defaultKaptcha")
  public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
      throws Exception {
    byte[] captchaChallengeAsJpeg = null;
    ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
    try {
      // 生产验证码字符串并保存到session中
      String createText = defaultKaptcha.createText();
      httpServletRequest.getSession().setAttribute("rightCode", createText);
      // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
      BufferedImage challenge = defaultKaptcha.createImage(createText);
      ImageIO.write(challenge, "jpg", jpegOutputStream);
    } catch (IllegalArgumentException e) {
      httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
    captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
    httpServletResponse.setHeader("Cache-Control", "no-store");
    httpServletResponse.setHeader("Pragma", "no-cache");
    httpServletResponse.setDateHeader("Expires", 0);
    httpServletResponse.setContentType("image/jpeg");
    ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
    responseOutputStream.write(captchaChallengeAsJpeg);
    responseOutputStream.flush();
    responseOutputStream.close();
  }

  /**
   * 3、校对验证码
   * @return string
   */
  @RequestMapping("/vrifyCode")
  @ResponseBody
  public String imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest,String tryCode) {
    String rightCode = (String) httpServletRequest.getSession().getAttribute("rightCode");
    System.out.println("rightCode:"+rightCode+" ———— tryCode:"+tryCode);
    if (!rightCode.equals(tryCode)) {
      return "错误的验证码";
    }
    return "ok";
  }

}

