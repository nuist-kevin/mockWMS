package com.focustech.mic.test.cb.listener;

import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by caiwen on 2017/4/20.
 */
public class ReceiptListListener implements MessageListener  {

    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                System.out.println("离开家里卡我家二楼空间啊离开教室地方了空间撒到了房间啊老师看见对方快乐撒大家");

                System.out.println(((TextMessage) message).getText());
                System.out.println(((ActiveMQTextMessage) message).getGroupID());
            } catch (JMSException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("Message must be of type TextMessage");
        }
    }
}
