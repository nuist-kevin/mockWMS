package com.focustech.mic.test.cb.controller;

import com.focustech.mic.test.cb.entity.command.UserCommand;
import com.focustech.mic.test.cb.entity.user.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author caiwen
 */
@Controller
@RequestMapping("/login")
public class LoginController {

  @RequestMapping(method = RequestMethod.GET)
  public String login() {
    return "login";
  }

  @RequestMapping(method = RequestMethod.POST)
  public ModelAndView login(UserCommand userCommand, RedirectAttributes redirectAttributes) {
    // TODO 登录校验逻辑
    ModelAndView modelAndView = new ModelAndView();
    User user = new User();
    BeanUtils.copyProperties(userCommand, user);
    redirectAttributes.addFlashAttribute("user", user);
    modelAndView.setViewName("redirect:/home");
    return modelAndView;
  }
}
