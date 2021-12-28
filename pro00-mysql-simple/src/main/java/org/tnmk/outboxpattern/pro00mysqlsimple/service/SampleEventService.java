package org.tnmk.outboxpattern.pro00mysqlsimple.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tnmk.outboxpattern.pro00mysqlsimple.entity.SampleEntity;
import org.tnmk.outboxpattern.pro00mysqlsimple.repository.SampleRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class SampleEventService {
  private final SampleRepository sampleRepository;

  public SampleEntity createEvent(SampleEntity sampleEvent) {
    log.info("Start event: {}", sampleEvent);
    SampleEntity result = sampleRepository.save(sampleEvent);
    log.info("End event: {}", result);
    return result;
  }

}
