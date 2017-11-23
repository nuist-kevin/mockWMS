package com.focustech.mic.test.cb.listener;

import static com.focustech.mic.test.cb.entity.BusinessType.OSS2WMS_ASN;
import static com.focustech.mic.test.cb.entity.BusinessType.OSS2WMS_DELLIST;

import com.focustech.mic.test.cb.sender.AsnMsgSender;
import com.focustech.mic.test.cb.sender.ConfirmMsgSender;
import com.focustech.mic.test.cb.sender.DoMsgSender;
import com.jayway.jsonpath.JsonPath;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.*;
import java.io.IOException;

/**
 * @author caiwen
 */

public class WmsMessageListener implements MessageListener{

  @Autowired
  private ConfirmMsgSender confirmMsgSender;

  @Autowired
  private DoMsgSender doMsgSender;

  @Autowired
  private AsnMsgSender asnMsgSender;

  @Override
  public void onMessage(Message message) {

    if (message instanceof TextMessage) {
      try {
        // 发送一般消息响应
        confirmMsgSender.confirm(message);

        String jsonMessage = ((ActiveMQTextMessage) message).getText();
        String businessType = JsonPath.read(jsonMessage, "$.businessType");

        if (OSS2WMS_DELLIST.toString().equals(businessType)) {
          //响应出库单消息
          doMsgSender.sendSuiteForMessage((ActiveMQTextMessage) message);
        }

        if (OSS2WMS_ASN.toString().equals(businessType)) {
          //响应入库单消息
          asnMsgSender.sendAsnResponse((TextMessage) message);
        }

      } catch (JMSException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      throw new IllegalArgumentException("Message must be of type TextMessage");
    }
  }

}
