package com.iwa.virtualtopicdemo.service;

import static com.iwa.virtualtopicdemo.jms.VirtuaTopicConstants.CONSUMER_ONE_VIRTUAL_TOPIC_DEMO_MESSAGE;
import static com.iwa.virtualtopicdemo.jms.VirtuaTopicConstants.CONSUMER_TWO_VIRTUAL_TOPIC_DEMO_MESSAGE;
import javax.jms.Session;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JmsScanHandler {

  @JmsListener(destination = CONSUMER_ONE_VIRTUAL_TOPIC_DEMO_MESSAGE)
  public void receiveMessageConsumerOne(
      @Payload final String messageReceived,
      @Headers final MessageHeaders headers,
      final Message<String> message,
      final Session session) {
    log.info("[CONSUMER ONE] Received: {}", messageReceived);
  }

  @JmsListener(destination = CONSUMER_TWO_VIRTUAL_TOPIC_DEMO_MESSAGE)
  public void receiveMessageConsumerTwo(
      @Payload final String messageReceived,
      @Headers final MessageHeaders headers,
      final Message<String> message,
      final Session session) {
    log.info("[CONSUMER TWO] Received: {}", messageReceived);
  }
}