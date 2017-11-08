package com.focustech.mic.test.cb.listener;

import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author caiwen
 */
public class MySessionAwareListener implements SessionAwareMessageListener {

  public void onMessage(Message message, Session session) throws JMSException {
    System.out.println(message.getJMSType());
  }
}
