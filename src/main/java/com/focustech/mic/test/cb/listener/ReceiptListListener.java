package com.focustech.mic.test.cb.listener;

import com.alibaba.fastjson.JSON;

import com.focustech.mic.test.cb.entity.AsnOrder;
import com.focustech.mic.test.cb.entity.DeliveryOrder;
import com.focustech.mic.test.cb.sender.ConfirmMsgSender;
import com.jayway.jsonpath.JsonPath;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.*;
import java.io.IOException;

/**
 * Created by caiwen on 2017/4/20.
 */
public class ReceiptListListener implements MessageListener  {

    @Autowired
    ConfirmMsgSender confirmMsgSender;

    public void onMessage(Message message) {

        if (message instanceof ActiveMQTextMessage) {
            try {
                // 发送一般消息响应
                confirmMsgSender.confirm(message);



                String jsonMessage = ((ActiveMQTextMessage) message).getText();
                String businessType = JsonPath.read(jsonMessage, "$.businessType");
                System.out.println(jsonMessage);
                System.out.println(((ActiveMQTextMessage) message).getProperties());

                if ("OSS2WMS_DELLIST".equals(businessType)) {
                       //响应出库单消息
                    DeliveryOrder deliveryOrder = JSON.parseObject(((TextMessage) message).getText(), DeliveryOrder.class);

                    // 消息是 出库单审核通过 ，通过 type 为 new 判断
                    if ("new".equals(deliveryOrder.getType())) {
                        // 发送响应消息

                    }
                }

                if ("OSS2WMS_ASN".equals(businessType)) {
                    //响应入库单消息
                    AsnOrder asnOrder =  JSON.parseObject(((TextMessage) message).getText(), AsnOrder.class);
                    if ("new".equals(asnOrder.getType())) {

                    }
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

    public void handleMessage(ActiveMQTextMessage message) {

    }

}
