package com.iwa.virtualtopicdemo.service;

import static com.iwa.virtualtopicdemo.jms.VirtuaTopicConstants.VIRTUAL_TOPIC_DEMO_MESSAGE;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DemoService {

  private final JmsTemplate jmsTemplate;

  public void sendMessage(final String message) {
    jmsTemplate.convertAndSend(new ActiveMQTopic(VIRTUAL_TOPIC_DEMO_MESSAGE), message);
    log.info("[SERVICE] Message sent to virtual topic: {}", VIRTUAL_TOPIC_DEMO_MESSAGE);
  }
}
