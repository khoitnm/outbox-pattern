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

  @GetMapping("/outbox/u/{outboxId}/success")
  public List<SampleEntity> uniqueOutboxSuccess(@PathVariable("outboxId") String outboxId) {
    return outboxPatternService.uniqueOutboxSuccess(outboxId);
  }

  @GetMapping("/outbox/u/{outboxId}/fail")
  public List<SampleEntity> uniqueOutboxFail(@PathVariable("outboxId") String outboxId) {
    return outboxPatternService.uniqueOutboxFail(outboxId);
  }

  @GetMapping("/outbox/random/success")
  public List<SampleEntity> outboxSuccess() {
    return outboxPatternService.outboxSuccess();
  }

  @GetMapping("/outbox/random/fail")
  public List<SampleEntity> outboxFail() {
    return outboxPatternService.outboxFail();
  }
}
