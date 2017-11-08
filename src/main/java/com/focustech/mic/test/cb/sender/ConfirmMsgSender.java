package com.focustech.mic.test.cb.sender;

import com.alibaba.fastjson.JSON;

import com.focustech.mic.test.cb.entity.wms.MsgConfirm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.Date;

/**
 * @author caiwen
 */
@Component
public class ConfirmMsgSender {

  @Autowired
  private JmsOperations jmsOperations;

  public void confirm(Message message) throws JMSException {
    final MsgConfirm msgConfirm = new MsgConfirm();
    msgConfirm.setAddTime(new Date());
    msgConfirm.setBusinessType("WMS2OSS_MSG_COMFIRM");
    msgConfirm.setFromSite("local");
    msgConfirm.setRecId(message.getStringProperty("recId"));
    msgConfirm.setStatus("1");
    final String jsonMsgConfirm = JSON.toJSONString(msgConfirm);
    System.out.println("发送消息：" + jsonMsgConfirm);
    jmsOperations.send(session -> session.createTextMessage(jsonMsgConfirm));
  }
}
