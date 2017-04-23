package com.focustech.mic.test.cb.sender;

import com.alibaba.fastjson.JSON;

import com.focustech.mic.test.cb.entity.DeliveryOrder;
import com.focustech.mic.test.cb.entity.DeliveryStatusMessage;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.io.IOException;

/**
 * Created by caiwen on 2017/4/21.
 */

@Component
public class DoMsgSender {

    @Autowired
    JmsOperations jmsOperations;


    private DeliveryStatusMessage buildMsgObjectFromMqMessage(Message message, String status) throws JMSException, IOException {
        DeliveryStatusMessage deliveryStatusMessage = new DeliveryStatusMessage();
        DeliveryOrder deliveryOrder = JSON.parseObject(((ActiveMQTextMessage)message).getText(), DeliveryOrder.class);
        deliveryStatusMessage.setStatus(status);
        deliveryStatusMessage.setCarrierService(deliveryOrder.getCarrierService());
        deliveryStatusMessage.setCompanyCode(deliveryOrder.getCompanyCode());
        deliveryStatusMessage.setMicComId(deliveryOrder.getMicComId());
        deliveryStatusMessage.setRelatedBill1(deliveryOrder.getRelatedBill1());
        deliveryStatusMessage.setWmsDoCode(""); //TODO WMSDOCODE
        return deliveryStatusMessage;

    }

    public String messageToJson(DeliveryStatusMessage deliveryStatusMessage) {
        return JSON.toJSONString(deliveryStatusMessage);

    }

    public void sendSuiteForMessage(Message message) throws IOException, JMSException {
        //发货单生效
        String activateDoMsg = messageToJson(buildMsgObjectFromMqMessage(message, "1"));
        jmsOperations.send(session -> session.createTextMessage(activateDoMsg));

        //拣货单完成
        String pickupfinishedMsg = messageToJson(buildMsgObjectFromMqMessage(message, "2"));
        jmsOperations.send(session -> session.createTextMessage(pickupfinishedMsg));

        //发货完成
        String finishedMsg = messageToJson(buildMsgObjectFromMqMessage(message, "3"));
        jmsOperations.send(session -> session.createTextMessage(finishedMsg));

        //过账


    }
}
