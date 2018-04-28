package com.caiwe;

import com.focustech.mic.test.cb.config.RootConfig;
import com.focustech.mic.test.cb.mq.sender.AsnMsgSender;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = RootConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SenderTest {

  @Autowired
  AsnMsgSender asnMsgSender;

  @Autowired
  JmsOperations jmsTemplate;



}
