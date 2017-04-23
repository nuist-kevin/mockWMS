package com.focustech.mic.test.cb.sender;

import com.alibaba.fastjson.JSONPath;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.swing.plaf.TableHeaderUI;

/**
 * Created by caiwen on 2017/4/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
public class ConfirmMsgSenderTest {

    @Autowired
    ConfirmMsgSender confirmMsgSender;

    @Autowired
    JmsOperations jmsOperations;

    @Test
    public void testSender() throws JMSException, InterruptedException {
        Message  message = new ActiveMQTextMessage();
        message.setStringProperty("recId", "123456");
        final ActiveMQTextMessage[] sendedMessage = {new ActiveMQTextMessage()};
        new Thread(() -> {
            sendedMessage[0] = (ActiveMQTextMessage) jmsOperations.receive();}, "testThread").start();
        confirmMsgSender.confirm(message);

        Assert.assertEquals(JSONPath.read(sendedMessage[0].getText(), "$.recId"),"123456");
    }
}
