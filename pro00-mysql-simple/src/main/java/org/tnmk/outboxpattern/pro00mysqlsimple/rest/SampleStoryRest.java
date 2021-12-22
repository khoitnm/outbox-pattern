package org.tnmk.outboxpattern.pro00mysqlsimple.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.tnmk.outboxpattern.pro00mysqlsimple.entity.SampleEntity;
import org.tnmk.outboxpattern.pro00mysqlsimple.service.SampleWithOutboxTransactionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleStoryRest {

  private final SampleWithOutboxTransactionService sampleWithOutboxTransactionService;

  @GetMapping("/outbox/{outboxId}")
  public List<SampleEntity> triggerOutbox(@PathVariable("outboxId") String outboxId) {
    return sampleWithOutboxTransactionService.executeWithOutbox(outboxId);
  }
}
