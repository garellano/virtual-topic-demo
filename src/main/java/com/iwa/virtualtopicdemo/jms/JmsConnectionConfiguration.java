package com.iwa.virtualtopicdemo.jms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

@EnableJms
@Configuration
public class JmsConnectionConfiguration {

  @Bean
  public JmsListenerContainerFactory<?> queueListenerFactory() {
    final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setMessageConverter(new MappingJackson2MessageConverter());
    return factory;
  }
}
