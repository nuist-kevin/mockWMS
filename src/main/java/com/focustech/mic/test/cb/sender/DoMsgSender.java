package com.focustech.mic.test.cb.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.focustech.mic.test.cb.entity.DeliveryOrder;
import com.focustech.mic.test.cb.entity.DeliveryStatusMessage;
import com.jayway.jsonpath.JsonPath;
import jdk.net.SocketFlow;
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

    ObjectMapper objectMapper = new ObjectMapper();

    private DeliveryStatusMessage buildMsgObjectFromMqMessage(Message message, String status) throws JMSException, IOException {
        DeliveryStatusMessage deliveryStatusMessage = new DeliveryStatusMessage();
        DeliveryOrder deliveryOrder = objectMapper.readValue(((ActiveMQTextMessage)message).getText(), DeliveryOrder.class);
        deliveryStatusMessage.setStatus(status);
        deliveryStatusMessage.setCarrierService(deliveryOrder.getCarrierService());
        deliveryStatusMessage.setCompanyCode(deliveryOrder.getCompanyCode());
        deliveryStatusMessage.setMicComId(deliveryOrder.getMicComId());
        deliveryStatusMessage.setRelatedBill1(deliveryOrder.getRelatedBill1());
        deliveryStatusMessage.setWmsDoCode("");
        return deliveryStatusMessage;

    }

    public String messageToJson(DeliveryStatusMessage deliveryStatusMessage) throws JsonProcessingException {
        return objectMapper.writeValueAsString(deliveryStatusMessage);

    }

    public void sendSuite() throws JsonProcessingException {
        String activateDoMsg = messageToJson(buildMsgObject("1"));
        jmsOperations.send(session -> { session.createTextMessage(

        )});
    }
}
