package org.tnmk.practicetransactionoutbox.pro00mysqlsimple.samplebusiness.service;

import com.gruelbox.transactionoutbox.TransactionOutbox;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.stereotype.Service;
import org.tnmk.practicetransactionoutbox.pro00mysqlsimple.samplebusiness.datafactory.SampleEntityFactory;
import org.tnmk.practicetransactionoutbox.pro00mysqlsimple.samplebusiness.entity.SampleEntity;
import org.tnmk.practicetransactionoutbox.pro00mysqlsimple.samplebusiness.repository.SampleRepository;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.tnmk.practicetransactionoutbox.pro00mysqlsimple.samplebusiness.service.ExceptionHelper.throwAnException;

@Service
@RequiredArgsConstructor
@Slf4j
public class SampleOutboxPublisher {
  private final TransactionOutbox transactionOutbox;
  private final SampleRepository sampleRepository;

  // Must have @Transactional. Otherwise, we'll get error "com.gruelbox.transactionoutbox.NoTransactionActiveException: null"
  @Transactional
  public List<SampleEntity> unique_allSuccess(String uniqueRequestId) {
    String now = formatCurrentDateTime();
    SampleEntity sampleEntity = SampleEntityFactory.withName("Entity_" + now);
    log.info("uniqueOutboxSuccess - start entity: {}", sampleEntity);
    SampleEntity sampleEntityResult = sampleRepository.save(sampleEntity);

    log.info("uniqueOutboxSuccess - start event: {}", sampleEntityResult);

//    MDC.put(MDCConstants.OUTBOX_UNIQUE_REQUEST_ID, uniqueRequestId);
    SampleEntity sampleEventResult = transactionOutbox.with()
        .uniqueRequestId(uniqueRequestId)
        .schedule(SampleOutboxConsumerJob.class)
        .updateSuccess(sampleEntityResult);
//    MDC.remove(MDCConstants.OUTBOX_UNIQUE_REQUEST_ID);

    List<SampleEntity> result = Arrays.asList(sampleEntityResult, sampleEventResult);
    log.info("uniqueOutboxSuccess - end: {}", result);
    return result;
  }

  // Must have @Transactional. Otherwise, we'll get error "com.gruelbox.transactionoutbox.NoTransactionActiveException: null"
  @Transactional
  public List<SampleEntity> unique_publishError(String uniqueRequestId) {
    String now = formatCurrentDateTime();
    SampleEntity sampleEntity = SampleEntityFactory.withName("Entity_" + now);
    log.info("uniqueOutboxFail - start entity: {}", sampleEntity);
    SampleEntity sampleEntityResult = sampleRepository.save(sampleEntity);

    log.info("uniqueOutboxFail - start event: {}", sampleEntityResult);
//    MDC.put(MDCConstants.OUTBOX_UNIQUE_REQUEST_ID, uniqueRequestId);
    SampleEntity sampleEventResult = transactionOutbox.with()
        .uniqueRequestId(uniqueRequestId)
        .schedule(SampleOutboxConsumerJob.class)
        .updateSuccess(sampleEntityResult);
//    MDC.remove(MDCConstants.OUTBOX_UNIQUE_REQUEST_ID);

    List<SampleEntity> result = Arrays.asList(sampleEntityResult, sampleEventResult);
    log.info("uniqueOutboxFail - end with an exception: {}", result);
    throwAnException();
    return result;
  }

  // Must have @Transactional. Otherwise, we'll get error "com.gruelbox.transactionoutbox.NoTransactionActiveException: null"
  @Transactional
  public List<SampleEntity> unique_consumeError(String uniqueRequestId) {
    String now = formatCurrentDateTime();
    SampleEntity sampleEntity = SampleEntityFactory.withName("Entity_" + now);
    log.info("uniqueOutboxNestedEventFail - start entity: {}", sampleEntity);
    SampleEntity sampleEntityResult = sampleRepository.save(sampleEntity);

    log.info("uniqueOutboxNestedEventFail - start event: {}", sampleEntityResult);
//    MDC.put(MDCConstants.OUTBOX_UNIQUE_REQUEST_ID, uniqueRequestId);
    SampleEntity sampleEventResult = transactionOutbox.with()
        .uniqueRequestId(uniqueRequestId)
        .schedule(SampleOutboxConsumerJob.class)
        .updateFail(sampleEntityResult);
//    MDC.remove(MDCConstants.OUTBOX_UNIQUE_REQUEST_ID);

    List<SampleEntity> result = Arrays.asList(sampleEntityResult, sampleEventResult);
    log.info("uniqueOutboxNestedEventFail - end: {}", result);
    return result;
  }

  // Must have @Transactional. Otherwise, we'll get error "com.gruelbox.transactionoutbox.NoTransactionActiveException: null"
  @Transactional
  public List<SampleEntity> noUnique_allSuccess() {
    String now = formatCurrentDateTime();
    SampleEntity sampleEntity = SampleEntityFactory.withName("Entity_" + now);
    log.info("outboxSuccess - start entity: {}", sampleEntity);
    SampleEntity sampleEntityResult = sampleRepository.save(sampleEntity);

    log.info("outboxSuccess - start event: {}", sampleEntityResult);

    SampleEntity sampleEventResult = transactionOutbox.with()
        .schedule(SampleOutboxConsumerJob.class)
        .updateSuccess(sampleEntityResult);

    List<SampleEntity> result = Arrays.asList(sampleEntityResult, sampleEventResult);
    log.info("outboxSuccess - finish: {}", result);
    return result;
  }

  // Must have @Transactional. Otherwise, we'll get error "com.gruelbox.transactionoutbox.NoTransactionActiveException: null"
  @Transactional
  public List<SampleEntity> noUnique_publishError() {
    String now = formatCurrentDateTime();
    SampleEntity sampleEntity = SampleEntityFactory.withName("Entity_" + now);
    log.info("outboxFail - start entity: {}", sampleEntity);
    SampleEntity sampleEntityResult = sampleRepository.save(sampleEntity);

    log.info("outboxFail - start event: {}", sampleEntityResult);

    SampleEntity sampleEventResult = transactionOutbox.with()
        .schedule(SampleOutboxConsumerJob.class)
        .updateSuccess(sampleEntityResult);

    List<SampleEntity> result = Arrays.asList(sampleEntityResult, sampleEventResult);
    log.info("outboxFail - end with an exception: {}", result);
    throwAnException();
    return result;
  }

  // Must have @Transactional. Otherwise, we'll get error "com.gruelbox.transactionoutbox.NoTransactionActiveException: null"
  @Transactional
  public List<SampleEntity> noUnique_consumeError() {
    String now = formatCurrentDateTime();
    SampleEntity sampleEntity = SampleEntityFactory.withName("Entity_" + now);
    log.info("outboxFail - start entity: {}", sampleEntity);
    SampleEntity sampleEntityResult = sampleRepository.save(sampleEntity);

    log.info("outboxFail - start event: {}", sampleEntityResult);
    SampleEntity sampleEventResult = transactionOutbox.with()
        .schedule(SampleOutboxConsumerJob.class)
        .updateFail(sampleEntityResult);

    List<SampleEntity> result = Arrays.asList(sampleEntityResult, sampleEventResult);
    log.info("outboxFail - end with an exception: {}", result);
    return result;
  }

  private String formatCurrentDateTime() {
    return ZonedDateTime.now().toString();
  }
}
