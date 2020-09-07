package com.iwa.virtualtopicdemo.service;

import static com.iwa.virtualtopicdemo.jms.VirtuaTopicConstants.CONSUMER_EMAIL_MESSAGE;
import static com.iwa.virtualtopicdemo.jms.VirtuaTopicConstants.CONSUMER_IN_APP_MESSAGE;
import javax.jms.Session;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JmsScanHandler {


  private final JmsTemplate jmsTemplate;

  private static final String FAILED_SUFFIX = "FAILED ONCE";

  @JmsListener(destination = CONSUMER_EMAIL_MESSAGE)
  public void receiveMessageEmailConsumer(
      @Payload final String messageReceived,
      @Headers final MessageHeaders headers,
      final Message<String> message,
      final Session session) {
    log.info("[CONSUMER EMAIL] Received: {} - SENDING EMAIL", messageReceived);
    randomPause();
    shootInTheFoot(messageReceived);
  }

  @JmsListener(destination = CONSUMER_IN_APP_MESSAGE)
  public void receiveMessageInAppConsumer(
      @Payload final String messageReceived,
      @Headers final MessageHeaders headers,
      final Message<String> message,
      final Session session) {
    log.info("[CONSUMER IN-APP] Received: {} - SENDING IN-APP NOTIFICATION", messageReceived);
    randomPause();
  }

  private void shootInTheFoot(final String message) {
    if(!StringUtils.endsWithIgnoreCase(message, FAILED_SUFFIX) && (int)(Math.random()*2)+1 == 2) {
      jmsTemplate.convertAndSend(new ActiveMQQueue(CONSUMER_EMAIL_MESSAGE), String.format("%s %s", message, FAILED_SUFFIX));
    }
  }

  private void randomPause() {
    try {
      Thread.sleep(((int)(Math.random()*4)+1)*1000);
    } catch (final InterruptedException e) {}
  }
}