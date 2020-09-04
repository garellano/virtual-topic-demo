package com.iwa.virtualtopicdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import com.iwa.virtualtopicdemo.service.DemoService;

@Configuration
public class DemoConfiguration {

  @Bean
  public DemoService demoService(final JmsTemplate jmsTemplate) {
    return new DemoService(jmsTemplate);
  }
}
