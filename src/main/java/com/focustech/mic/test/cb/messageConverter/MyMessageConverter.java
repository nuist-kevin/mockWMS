package com.focustech.mic.test.cb.messageConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.focustech.mic.test.cb.entity.DeliveryOrder;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.soap.Text;
import java.io.IOException;

/**
 * Created by caiwen on 2017/4/21.
 */
public class MyMessageConverter implements MessageConverter {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        Message message = null;
        try {
            message = session.createTextMessage(objectMapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        try {
            deliveryOrder = objectMapper.
                    readValue(((TextMessage)message).getText(), DeliveryOrder.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return deliveryOrder;
    }
}
