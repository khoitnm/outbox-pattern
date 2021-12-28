package org.tnmk.outboxpattern.pro00mysqlsimple.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.tnmk.outboxpattern.pro00mysqlsimple.entity.SampleEntity;
import org.tnmk.outboxpattern.pro00mysqlsimple.service.OutboxPatternService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleStoryRest {

  private final OutboxPatternService outboxPatternService;

  @GetMapping("/outbox/unique/{outboxId}")
  public List<SampleEntity> triggerOutboxUnqiue(@PathVariable("outboxId") String outboxId) {
    return outboxPatternService.uniqueOutboxSuccess(outboxId);
  }

  @GetMapping("/outbox/nounique")
  public List<SampleEntity> triggerOutbox() {
    return outboxPatternService.outboxSuccess();
  }

  @GetMapping("/outbox/nounique/fail")
  public List<SampleEntity> triggerOutboxFail() {
    return outboxPatternService.outboxFail();
  }
}
