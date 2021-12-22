package org.tnmk.outboxpattern.pro00mysqlsimple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tnmk.outboxpattern.pro00mysqlsimple.datafactory.SampleEntityFactory;
import org.tnmk.outboxpattern.pro00mysqlsimple.entity.SampleEntity;
import org.tnmk.outboxpattern.pro00mysqlsimple.service.SampleService;

@Slf4j
@Service
public class SeedDataStory {

  @Autowired
  private SampleService sampleService;

  @EventListener(ApplicationReadyEvent.class)
  public void autoStart() {
    SampleEntity sampleEntity = SampleEntityFactory.random();
    sampleEntity = sampleService.create(sampleEntity);
    log.info("SampleEntity: " + sampleEntity);
  }
}
