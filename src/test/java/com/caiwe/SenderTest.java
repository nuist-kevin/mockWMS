package com.caiwe;

import com.focustech.mic.test.cb.config.RootConfig;
import com.focustech.mic.test.cb.entity.mount.AsnOrder;
import com.focustech.mic.test.cb.sender.AsnMsgSender;
import java.io.IOException;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = RootConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SenderTest {

  @Autowired
  AsnMsgSender asnMsgSender;

  @Autowired
  JmsOperations jmsTemplate;

  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  @Test
  public void testRedis() {
    redisTemplate.opsForList().rightPush("messageIdList", "234533");
    redisTemplate.opsForValue()
        .set("234533", "hello redis");
  }

/*//  @Test
  public void test() throws IOException, JMSException {
//    AsnOrder asnOrder = new AsnOrder();
//    asnOrder.setBillTypeCode("22");
//    asnMsgSender.arrivalRegister(asnOrder);
    jmsTemplate.send("WMS2OSS", new MessageCreator() {
      @Override
      public Message createMessage(Session session) throws JMSException {
        return session.createTextMessage("hello");
      }
    });
  }*/

}
