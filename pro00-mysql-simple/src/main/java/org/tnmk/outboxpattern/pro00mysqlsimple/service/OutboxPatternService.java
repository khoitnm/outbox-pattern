package org.tnmk.outboxpattern.pro00mysqlsimple.service;

import com.gruelbox.transactionoutbox.TransactionOutbox;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tnmk.outboxpattern.pro00mysqlsimple.datafactory.SampleEntityFactory;
import org.tnmk.outboxpattern.pro00mysqlsimple.entity.SampleEntity;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxPatternService {
  private final TransactionOutbox transactionOutbox;
  private final SampleDataService sampleDataService;

  // Must have @Transactional. Otherwise, we'll get error "com.gruelbox.transactionoutbox.NoTransactionActiveException: null"
  @Transactional
  public List<SampleEntity> uniqueOutboxSuccess(String outboxUniqueId) {
    SampleEntity sampleEntity = SampleEntityFactory.random();
    SampleEntity sampleEntityResult = sampleDataService.create(sampleEntity);

    SampleEntity sampleEvent = SampleEntityFactory.random();
    SampleEntity sampleEventResult = transactionOutbox.with()
        .uniqueRequestId(outboxUniqueId)
        .schedule(SampleEventService.class)
        .createEvent(sampleEvent);

    List<SampleEntity> result = Arrays.asList(sampleEntityResult, sampleEventResult);
    log.info("OutboxPatternService.uniqueOutboxSuccess finish: {}", result);
    return result;
  }

  // Must have @Transactional. Otherwise, we'll get error "com.gruelbox.transactionoutbox.NoTransactionActiveException: null"
  @Transactional
  public List<SampleEntity> outboxSuccess() {
    SampleEntity sampleEntity = SampleEntityFactory.random();
    log.info("OutboxPatternService.outboxSuccess start entity: {}", sampleEntity);
    SampleEntity sampleEntityResult = sampleDataService.create(sampleEntity);

    SampleEntity sampleEvent = SampleEntityFactory.random();
    log.info("OutboxPatternService.outboxSuccess start event: {}", sampleEvent);

    SampleEntity sampleEventResult = transactionOutbox.with()
        .schedule(SampleEventService.class)
        .createEvent(sampleEvent);

    List<SampleEntity> result = Arrays.asList(sampleEntityResult, sampleEventResult);
    log.info("OutboxPatternService.outboxSuccess finish: {}", result);
    return result;
  }
}
