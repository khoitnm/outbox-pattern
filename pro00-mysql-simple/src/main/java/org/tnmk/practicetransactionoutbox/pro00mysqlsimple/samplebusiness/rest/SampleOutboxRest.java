package org.tnmk.practicetransactionoutbox.pro00mysqlsimple.samplebusiness.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.tnmk.practicetransactionoutbox.pro00mysqlsimple.samplebusiness.entity.SampleEntity;
import org.tnmk.practicetransactionoutbox.pro00mysqlsimple.samplebusiness.service.SampleOutboxPublisher;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleOutboxRest {

  private final SampleOutboxPublisher sampleOutboxPublisher;

  @GetMapping("/outbox/unique/{outboxId}/all/success")
  public List<SampleEntity> unique_allSuccess(@PathVariable("outboxId") String outboxId) {
    return sampleOutboxPublisher.unique_allSuccess(outboxId);
  }

  @GetMapping("/outbox/unique/{outboxId}/publish/error")
  public List<SampleEntity> unique_publishError(@PathVariable("outboxId") String outboxId) {
    return sampleOutboxPublisher.unique_publishError(outboxId);
  }

  @GetMapping("/outbox/unique/{outboxId}/consume/error")
  public List<SampleEntity> unique_consumeError(@PathVariable("outboxId") String outboxId) {
    return sampleOutboxPublisher.unique_consumeError(outboxId);
  }

  @GetMapping("/outbox/no-unique/all/success")
  public List<SampleEntity> noUnique_allSuccess() {
    return sampleOutboxPublisher.noUnique_allSuccess();
  }

  @GetMapping("/outbox/no-unique/publish/error")
  public List<SampleEntity> noUnique_publishError() {
    return sampleOutboxPublisher.noUnique_publishError();
  }

  @GetMapping("/outbox/no-unique/consume/error")
  public List<SampleEntity> noUnique_consumeError() {
    return sampleOutboxPublisher.noUnique_consumeError();
  }
}
