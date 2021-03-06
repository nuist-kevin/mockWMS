package com.focustech.mic.test.cb.controller;

import javax.jms.Destination;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author caiwen
 */
@Controller
@RequestMapping("/")
public class HomeController implements ApplicationContextAware {

  private ApplicationContext applicationContext;

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public String home(Model model) {
    model.addAttribute("queueNameSet",
        applicationContext.getParent().getBeansOfType(Destination.class).keySet());
    return "home";
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
