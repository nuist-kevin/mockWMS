package com.focustech.mic.test.cb.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.focustech.mic.test.cb.entity.MsgConfirm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.Date;

/**
 * Created by caiwen on 2017/4/21.
 */
@Component
public class ConfirmMsgSender {

    @Autowired
    private JmsOperations jmsOperations;

    ObjectMapper objectMapper = new ObjectMapper();

    public void confirmMessage(Message message) throws JMSException, JsonProcessingException {
        final MsgConfirm msgConfirm = new MsgConfirm();
        msgConfirm.setAddTime(new Date());
        msgConfirm.setBusinessType("WMS2OSS_MSG_COMFIRM");
        msgConfirm.setFromSite("local");
        msgConfirm.setRecId(message.getStringProperty("recId"));
        msgConfirm.setStatus("1");
        final String jsonMsgConfirm = objectMapper.writeValueAsString(msgConfirm);
        System.out.println("发送消息：" + jsonMsgConfirm );
        jmsOperations.send(session -> session.createTextMessage(jsonMsgConfirm));
    }
}
