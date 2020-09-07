package com.iwa.virtualtopicdemo.service;

import static com.iwa.virtualtopicdemo.jms.VirtuaTopicConstants.VIRTUAL_TOPIC_NOTIFICATION_MESSAGE;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DemoService {

  private final JmsTemplate jmsTemplate;

  private final String MESSAGE_FMT = "[%d] %s";

  public void sendMessage(final String message) {
    for (int i = 0; i < 20; i++) {
      jmsTemplate.convertAndSend(new ActiveMQTopic(VIRTUAL_TOPIC_NOTIFICATION_MESSAGE), String.format(MESSAGE_FMT, i, message));
    }
    log.info("[SERVICE] Message sent to virtual topic: {}", VIRTUAL_TOPIC_NOTIFICATION_MESSAGE);
  }
}
