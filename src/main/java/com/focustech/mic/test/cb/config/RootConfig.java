package com.focustech.mic.test.cb.config;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.focustech.mic.test.cb.mq.listener.WmsMessageListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;
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

  @Bean
  @Profile("home")
  public ConnectionFactory homeConnectionFactory() {
    return new ActiveMQConnectionFactory();
  }

  @Bean
  @Profile("work")
  public ConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    connectionFactory.setBrokerURL(brokerUrl);
    connectionFactory.setUserName(mqUsername);
    connectionFactory.setPassword(mqPassword);
    return connectionFactory;
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
    MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
    ObjectMapper objectMapper = new ObjectMapper();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

    objectMapper.registerModule(
        new JavaTimeModule()
            .addDeserializer(LocalDate.class, new LocalDateDeserializer(dateTimeFormatter))
            .addSerializer(LocalDate.class, new LocalDateSerializer(dateTimeFormatter))
    );
    objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.setSerializationInclusion(Include.NON_ABSENT);
    messageConverter.setObjectMapper(objectMapper);
    messageConverter.setTargetType(MessageType.TEXT);
    jmsTemplate.setMessageConverter(messageConverter);
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
  public MessageListenerContainer jmsContainer(ConnectionFactory connectionFactory) {
    DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();
    messageListenerContainer.setConnectionFactory(connectionFactory);
    messageListenerContainer.setDestination(oss2wms());
    messageListenerContainer.setMessageListener(messageListener());
    messageListenerContainer.setSessionTransacted(true);
    return messageListenerContainer;
  }

  @Bean
  @Profile("work")
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
    dataSource.setUrl("jdbc:oracle:thin:@192.168.17.104:1521:CBTEST");
    dataSource.setUsername("cb");
    dataSource.setPassword("cb1234");
    return dataSource;
  }

  @Bean
  @Profile("home")
  public DataSource mysqlDataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost:3306/CBTEST");
    dataSource.setUsername("test");
    dataSource.setPassword("test1234");
    return dataSource;
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    jdbcTemplate.setDataSource(dataSource);
    return jdbcTemplate;
  }

}
