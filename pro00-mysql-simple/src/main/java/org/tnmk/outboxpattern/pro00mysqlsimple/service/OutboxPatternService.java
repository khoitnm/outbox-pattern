package org.tnmk.outboxpattern.pro00mysqlsimple.service;

import com.gruelbox.transactionoutbox.TransactionOutbox;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tnmk.outboxpattern.pro00mysqlsimple.datafactory.SampleEntityFactory;
import org.tnmk.outboxpattern.pro00mysqlsimple.entity.SampleEntity;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
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
    String now = ZonedDateTime.now().toString();
    SampleEntity sampleEntity = SampleEntityFactory.withName("Entity_" + now);
    log.info("uniqueOutboxSuccess start entity: {}", sampleEntity);
    SampleEntity sampleEntityResult = sampleDataService.create(sampleEntity);

    SampleEntity sampleEvent = SampleEntityFactory.withName("Event_" + now);
    log.info("uniqueOutboxSuccess start event: {}", sampleEvent);

    SampleEntity sampleEventResult = transactionOutbox.with()
        .uniqueRequestId(outboxUniqueId)
        .schedule(SampleEventService.class)
        .createEvent(sampleEvent);

    List<SampleEntity> result = Arrays.asList(sampleEntityResult, sampleEventResult);
    log.info("uniqueOutboxSuccess: {}", result);
    return result;
  }

  // Must have @Transactional. Otherwise, we'll get error "com.gruelbox.transactionoutbox.NoTransactionActiveException: null"
  @Transactional
  public List<SampleEntity> uniqueOutboxFail(String outboxUniqueId) {
    String now = ZonedDateTime.now().toString();
    SampleEntity sampleEntity = SampleEntityFactory.withName("Entity_" + now);
    log.info("uniqueOutboxFail start entity: {}", sampleEntity);
    SampleEntity sampleEntityResult = sampleDataService.create(sampleEntity);

    SampleEntity sampleEvent = SampleEntityFactory.withName("Event_" + now);
    log.info("uniqueOutboxFail start event: {}", sampleEvent);

    SampleEntity sampleEventResult = transactionOutbox.with()
        .uniqueRequestId(outboxUniqueId)
        .schedule(SampleEventService.class)
        .createEvent(sampleEvent);

    List<SampleEntity> result = Arrays.asList(sampleEntityResult, sampleEventResult);
    log.info("uniqueOutboxFail with throw exception: {}", result);
    throwException();
    return result;
  }

  // Must have @Transactional. Otherwise, we'll get error "com.gruelbox.transactionoutbox.NoTransactionActiveException: null"
  @Transactional
  public List<SampleEntity> outboxSuccess() {
    String now = ZonedDateTime.now().toString();
    SampleEntity sampleEntity = SampleEntityFactory.withName("Entity_" + now);
    log.info("outboxSuccess start entity: {}", sampleEntity);
    SampleEntity sampleEntityResult = sampleDataService.create(sampleEntity);

    SampleEntity sampleEvent = SampleEntityFactory.withName("Event_" + now);
    log.info("outboxSuccess start event: {}", sampleEvent);

    SampleEntity sampleEventResult = transactionOutbox.with()
        .schedule(SampleEventService.class)
        .createEvent(sampleEvent);

    List<SampleEntity> result = Arrays.asList(sampleEntityResult, sampleEventResult);
    log.info("outboxSuccess finish: {}", result);
    return result;
  }

  // Must have @Transactional. Otherwise, we'll get error "com.gruelbox.transactionoutbox.NoTransactionActiveException: null"
  @Transactional
  public List<SampleEntity> outboxFail() {
    String now = ZonedDateTime.now().toString();
    SampleEntity sampleEntity = SampleEntityFactory.withName("Entity_" + now);
    log.info("outboxFail start entity: {}", sampleEntity);
    SampleEntity sampleEntityResult = sampleDataService.create(sampleEntity);

    SampleEntity sampleEvent = SampleEntityFactory.withName("Event_" + now);
    log.info("outboxFail start event: {}", sampleEvent);

    SampleEntity sampleEventResult = transactionOutbox.with()
        .schedule(SampleEventService.class)
        .createEvent(sampleEvent);

    List<SampleEntity> result = Arrays.asList(sampleEntityResult, sampleEventResult);
    log.info("outboxFail with throw exception: {}", result);
    throwException();
    return result;
  }

  private void throwException() {
    String a = null;
    a.toString();
  }
}
