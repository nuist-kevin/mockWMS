package com.focustech.mic.test.cb.mq.sender;

import com.alibaba.fastjson.JSON;
import com.focustech.mic.test.cb.mq.entity.wms.MsgConfirm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * @author caiwen
 */
@Component
public class ConfirmMsgSender {

  private final Logger logger = LoggerFactory.getLogger(ConfirmMsgSender.class);

  @Autowired
  private JmsOperations jmsOperations;

  public void confirm(Message message) throws JMSException {
    MsgConfirm msgConfirm = new MsgConfirm();
    msgConfirm.setRecId(message.getStringProperty("recId"));
    final String jsonMsgConfirm = JSON.toJSONString(msgConfirm);
    logger.debug(jsonMsgConfirm);
    jmsOperations.send(session -> session.createTextMessage(jsonMsgConfirm));
  }
}
