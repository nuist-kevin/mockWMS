package com.focustech.mic.test.cb.listener;

import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by caiwen on 2017/4/20.
 */
public class MySessionAwareListener implements SessionAwareMessageListener {

    public void onMessage(Message message, Session session) throws JMSException {
        System.out.println(message.getJMSType());
    }
}
