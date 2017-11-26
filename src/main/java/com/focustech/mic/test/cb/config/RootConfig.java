package com.focustech.mic.test.cb.config;

import com.focustech.mic.test.cb.converter.JsonMessageConverter;
import com.focustech.mic.test.cb.listener.WmsMessageListener;

import javax.jms.Message;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageListener;
import javax.sql.DataSource;

/**
 * @author caiwen
 */
@Configuration
@ComponentScan(basePackages = "com.focustech.mic.test.cb",
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
@PropertySource("classpath:application.properties")
public class RootConfig {

  @Value("${brokerUrl}")
  private String brokerUrl;
  @Value("${mqUsername}")
  private String mqUsername;
  @Value("${mqPassword}")
  private String mqPassword;
  @Value("${redis.host}")
  private String redisHost;
  @Value("${redis.port}")
  private int redisPort;

  @Bean
  public JedisConnectionFactory jedisConnectionFactory() {
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    jedisConnectionFactory.setHostName(redisHost);
    jedisConnectionFactory.setPort(redisPort);
    jedisConnectionFactory.setUsePool(true);
    return jedisConnectionFactory;
  }

  @Bean
  public RedisTemplate redisTemplate() {
    RedisTemplate<String, String> redisTemplate = new StringRedisTemplate();
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    return redisTemplate;
  }

  @Bean
  public ConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    if (!"".equals(brokerUrl)) {
      connectionFactory.setBrokerURL(brokerUrl);
      connectionFactory.setUserName(mqUsername);
      connectionFactory.setPassword(mqPassword);
    } else {
      connectionFactory.setBrokerURL("tcp://0.0.0.0:61616");
    }
    return connectionFactory;
  }

  @Bean
  public MessageConverter messageConverter() {
    return new JsonMessageConverter();
  }

  @Bean
  public Destination wms2oss() {
    return new ActiveMQQueue("WMS2OSS");
  }

  @Bean
  public JmsOperations jmsTemplate() {
    JmsTemplate jmsTemplate = new JmsTemplate();
    jmsTemplate.setConnectionFactory(connectionFactory());
    jmsTemplate.setDefaultDestination(wms2oss());
    jmsTemplate.setMessageConverter(messageConverter());
    return jmsTemplate;
  }

  @Bean
  public Destination oss2wms() {
    return new ActiveMQQueue("OSS2WMS");
  }

  @Bean
  public MessageListener messageListener() {
    return new WmsMessageListener();
  }

  @Bean
  public MessageListenerContainer jmsContainer() {
    DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();
    messageListenerContainer.setConnectionFactory(connectionFactory());
    messageListenerContainer.setDestination(oss2wms());
    messageListenerContainer.setMessageListener(messageListener());
    messageListenerContainer.setMessageConverter(messageConverter());
    return messageListenerContainer;
  }

  @Bean
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
    dataSource.setUrl("jdbc:oracle:thin:@192.168.17.104:1521:CBTEST");
    dataSource.setUsername("cb");
    dataSource.setPassword("cb1234");
    return dataSource;
  }

  @Bean
  public JdbcTemplate jdbcTemplate() {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    jdbcTemplate.setDataSource(dataSource());
    return jdbcTemplate;
  }
}
