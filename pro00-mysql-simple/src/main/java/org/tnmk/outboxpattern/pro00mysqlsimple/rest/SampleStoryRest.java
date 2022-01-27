package org.tnmk.outboxpattern.pro00mysqlsimple.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.tnmk.outboxpattern.pro00mysqlsimple.entity.SampleEntity;
import org.tnmk.outboxpattern.pro00mysqlsimple.service.OutboxPublisher;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleStoryRest {

  private final OutboxPublisher outboxPublisher;

  @GetMapping("/outbox/unique/{outboxId}/all/success")
  public List<SampleEntity> unique_allSuccess(@PathVariable("outboxId") String outboxId) {
    return outboxPublisher.unique_publishSuccess_and_consumeSuccess(outboxId);
  }

  @GetMapping("/outbox/unique/{outboxId}/publish/error")
  public List<SampleEntity> unique_publishError(@PathVariable("outboxId") String outboxId) {
    return outboxPublisher.unique_publishError(outboxId);
  }

  @GetMapping("/outbox/unique/{outboxId}/consume/error")
  public List<SampleEntity> unique_consumeError(@PathVariable("outboxId") String outboxId) {
    return outboxPublisher.unique_consumeError(outboxId);
  }

  @GetMapping("/outbox/no-unique/all/success")
  public List<SampleEntity> noUnique_allSuccess() {
    return outboxPublisher.noUnique_publishSuccess_and_consumeSuccess();
  }

  @GetMapping("/outbox/no-unique/publish/error")
  public List<SampleEntity> noUnique_publishError() {
    return outboxPublisher.noUnique_publishError_and_consumeNoError();
  }

  @GetMapping("/outbox/no-unique/consume/error")
  public List<SampleEntity> noUnique_consumeError() {
    return outboxPublisher.noUnique_publishSuccess_and_consumeError();
  }
}
