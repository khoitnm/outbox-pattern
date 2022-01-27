package org.tnmk.outboxpattern.pro00mysqlsimple.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tnmk.outboxpattern.pro00mysqlsimple.entity.SampleEntity;
import org.tnmk.outboxpattern.pro00mysqlsimple.repository.SampleRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxConsumer {
  private final SampleRepository sampleRepository;

  @Transactional
  public SampleEntity updateSuccess(SampleEntity sampleEvent) {
    return update(sampleEvent);
  }

  @Transactional
  public SampleEntity updateFail(SampleEntity sampleEvent) {
    SampleEntity result = update(sampleEvent);
    ExceptionUtils.throwAnException();
    return result;
  }

  private SampleEntity update(SampleEntity sampleEvent) {
    log.info("Start event: {}", sampleEvent);
    if (sampleEvent.getId() == null || sampleEvent.getId() == 0) {
      throw new IllegalArgumentException("Cannot update an entity without Id");
    }
    sampleEvent.setName("Edited_" + sampleEvent.getName());
    SampleEntity result = sampleRepository.save(sampleEvent);
    log.info("End event: {}", result);
    return result;
  }
}
