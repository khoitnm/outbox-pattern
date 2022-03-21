package org.tnmk.outboxpattern.pro00mysqlsimple.samplebusiness.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tnmk.outboxpattern.pro00mysqlsimple.common.outbox.outbox_config.TransactionalOutboxBackgroundWorker;
import org.tnmk.outboxpattern.pro00mysqlsimple.samplebusiness.entity.SampleEntity;
import org.tnmk.outboxpattern.pro00mysqlsimple.samplebusiness.repository.SampleRepository;

import javax.transaction.Transactional;

/**
 * This job will be processed by the submitter.
 * If it failed, the {@link TransactionalOutboxBackgroundWorker} will retry it.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SampleOutboxConsumerJob {
  private final SampleRepository sampleRepository;
  @Transactional
  public SampleEntity updateSuccess(SampleEntity sampleEvent) {
    return update(sampleEvent);
  }

  @Transactional
  public SampleEntity updateFail(SampleEntity sampleEvent) {
    SampleEntity result = update(sampleEvent);
    ExceptionHelper.throwAnException();
    return result;
  }

  private SampleEntity update(SampleEntity sampleEvent) {
    log.info("OutboxConsumer: start {}", sampleEvent);
    if (sampleEvent.getId() == null || sampleEvent.getId() == 0) {
      throw new IllegalArgumentException("Cannot update an entity without Id");
    }
    sampleEvent.setName("Edited_" + sampleEvent.getName());
    SampleEntity result = sampleRepository.save(sampleEvent);
    log.info("OutboxConsumer: end {}", result);
    return result;
  }
}
