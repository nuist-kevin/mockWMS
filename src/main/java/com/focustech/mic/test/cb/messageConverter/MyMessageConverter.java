package com.focustech.mic.test.cb.messageConverter;


import com.alibaba.fastjson.JSON;
import com.focustech.mic.test.cb.entity.mount.DeliveryOrder;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author caiwen
 */
public class MyMessageConverter implements MessageConverter {

  @Override
  public Message toMessage(Object object, Session session)
      throws JMSException, MessageConversionException {
    Message message = session.createTextMessage(JSON.toJSONString(object));
    return message;
  }

  @Override
  public Object fromMessage(Message message) throws JMSException, MessageConversionException {
    DeliveryOrder deliveryOrder = JSON
        .parseObject(((TextMessage) message).getText(), DeliveryOrder.class);
    return deliveryOrder;
  }
}
