package com.iwa.virtualtopicdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.iwa.virtualtopicdemo.service.DemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/demo/{message}")
@RequiredArgsConstructor
public class DemoController {

  private final DemoService demoService;

  @PostMapping
  public ResponseEntity<Void> createScan(@PathVariable("message") final String message) {
    log.info(
        "[CONTROLLER] message:{}",
        message);

        demoService.sendMessage(message);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
