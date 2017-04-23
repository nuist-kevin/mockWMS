package com.focustech.mic.test.cb.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by caiwen on 2017/4/20.
 */
@Component
public class MessageService {

    @JmsListener(destination = "OSS2WMS", selector = "JMSXGroupID='myGroup2'" )
    public void processMessage(String message) {
        System.out.println("注解监听器");
        System.out.println(message);
    }
}
