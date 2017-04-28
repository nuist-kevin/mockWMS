package com.focustech.mic.test.cb.sender;

import com.alibaba.fastjson.JSONPath;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.Connector;
import org.apache.activemq.broker.jmx.ProducerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.apache.activemq.broker.jmx.SubscriptionViewMBean;
import org.apache.activemq.broker.jmx.TopicViewMBean;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.activemq.web.LocalBrokerFacade;
import org.apache.activemq.web.RemoteJMXBrokerFacade;
import org.apache.activemq.web.config.JNDIConfiguration;
import org.apache.activemq.web.config.SystemPropertiesConfiguration;
import org.apache.activemq.web.config.WebConsoleConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.*;
import javax.management.*;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnector;
import javax.swing.plaf.TableHeaderUI;
import java.net.MalformedURLException;
import java.util.*;

/**
 * Created by caiwen on 2017/4/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
public class ConfirmMsgSenderTest {

    @Autowired
    ConfirmMsgSender confirmMsgSender;

    @Autowired
    JmsOperations jmsOperations;

    @Autowired
    @Qualifier("connectionFactory")
    ConnectionFactory connectionFactory;

    @Test
    public void testSender() throws JMSException, InterruptedException {
        Message  message = new ActiveMQTextMessage();
        message.setStringProperty("recId", "123456");
        final ActiveMQTextMessage[] sendedMessage = {new ActiveMQTextMessage()};
        new Thread(() -> {
            sendedMessage[0] = (ActiveMQTextMessage) jmsOperations.receive();}, "testThread").start();
        confirmMsgSender.confirm(message);

        Assert.assertEquals(JSONPath.read(sendedMessage[0].getText(), "$.recId"),"123456");
    }

    @Test
    public void testViewer() throws Exception {
        RemoteJMXBrokerFacade remoteJMXBrokerFacade = new RemoteJMXBrokerFacade();
        remoteJMXBrokerFacade.setBrokerName("wms_CB_MQ");
 /*       System.setProperty("webconsole.jmx.url", "service:jmx:rmi:///jndi/rmi://192.168.43.22:1099/jmxrmi");
        System.setProperty("webconsole.jmx.user", "admin");
        System.setProperty("webconsole.jmx.password", "admin2016");

        WebConsoleConfiguration webConsoleConfiguration = new SystemPropertiesConfiguration();*/

        WebConsoleConfiguration webConsoleConfiguration = new WebConsoleConfiguration() {
            @Override
            public ConnectionFactory getConnectionFactory() {
                return connectionFactory;
            }

            @Override
            public Collection<JMXServiceURL> getJmxUrls() {
                Collection<JMXServiceURL> jmxServiceURLS =  new ArrayList<>();
                JMXServiceURL jmxServiceURL;
                try {
                    jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://192.168.43.22:1099/jmxrmi");
                    jmxServiceURLS.add(jmxServiceURL);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return jmxServiceURLS;
            }

            @Override
            public String getJmxUser() {
                return null;
            }

            @Override
            public String getJmxPassword() {
                return null;
            }
        };

        remoteJMXBrokerFacade.setConfiguration(webConsoleConfiguration);
//        remoteJMXBrokerFacade.c
//        System.out.println(remoteJMXBrokerFacade.getQueue("OSS2WMS").toString());
//        remoteJMXBrokerFacade.getQueue("OSS2WMS").sendTextMessage("testmessage");

        for ( SubscriptionViewMBean consumer : remoteJMXBrokerFacade.getQueueConsumers("WMS2OSS")) {
            System.out.println(consumer.getClientId());
            System.out.println(consumer.getConnectionId());
            System.out.println(consumer.getDequeueCounter());

        }

        for ( ProducerViewMBean producer : remoteJMXBrokerFacade.getQueueProducers("WMS2OSS")) {
            System.out.println(producer.getClientId());
            System.out.println(producer.getConnectionId());
            System.out.println(producer.getSentCount());
        }

        for (QueueViewMBean queueViewMBean: remoteJMXBrokerFacade.getQueues() ) {
            System.out.println(queueViewMBean.getName());
            for (CompositeData compositeData :queueViewMBean.browse()) {
//                for (Object message: compositeData.values()) {
//                    System.out.println(compositeData.getClass().getName());
//                    System.out.println(compositeData.getCompositeType().getClassName());
//                    System.out.println(compositeData.getCompositeType().getTypeName());
//                    System.out.println(message);
//                }
                System.out.println(compositeData.get("Text"));
                System.out.println(compositeData.get("JMSMessageID"));
            }
        }
//        for (TopicViewMBean queueViewMBean: remoteJMXBrokerFacade.getTopics() ) {
//            System.out.println(queueViewMBean.getName());
//        }


    }

    @Test
    public void testJmx() throws Exception {
        String surl = "service:jmx:rmi:///jndi/rmi://192.168.43.22:1099/jmxrmi";

        JMXServiceURL url = new JMXServiceURL(surl);
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

        System.out.println("Domains:---------------");
        String domains[] = mbsc.getDomains();
        for (int i = 0; i < domains.length; i++) {
            System.out.println("\tDomain[" + i + "] = " + domains[i]);
        }

        System.out.println("all ObjectName：---------------");
        Set<ObjectInstance> set = mbsc.queryMBeans(null, null);
        for (Iterator<ObjectInstance> it = set.iterator(); it.hasNext();) {
            ObjectInstance oi = (ObjectInstance) it.next();
            System.out.println("\t" + oi.getObjectName());
        }

        System.out.println("org.apache.activemq:BrokerName=localhost,Type=Broker：---------------");
        ObjectName mbeanName = new ObjectName("org.apache.activemq:brokerName=wms_CB_MQ,type=Broker,destinationType=Queue,destinationName=wms2OssService.synWmsOrderStatus");
        MBeanInfo info = mbsc.getMBeanInfo(mbeanName);
        System.out.println("Class: " + info.getClassName());
        if (info.getAttributes().length > 0){
            for(MBeanAttributeInfo m : info.getAttributes())
                System.out.println("\t ==> Attriber：" + m.getName());
        }
        if (info.getOperations().length > 0){
            for(MBeanOperationInfo m : info.getOperations())
                System.out.println("\t ==> Operation：" + m.getName());
        }
        System.out.println(((CompositeDataSupport)
                mbsc.invoke(mbeanName, "getMessage", new Object[]{"ID:caiwen01-55556-1492412012540-1:2:1:1:1"}, new String[]{String.class.getName()})
        ).get("Text"));
        jmxc.close();
    }


}
