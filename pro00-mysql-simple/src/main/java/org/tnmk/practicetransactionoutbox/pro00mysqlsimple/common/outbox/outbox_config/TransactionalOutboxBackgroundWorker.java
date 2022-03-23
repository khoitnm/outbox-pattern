package org.tnmk.practicetransactionoutbox.pro00mysqlsimple.common.outbox.outbox_config;

import com.gruelbox.transactionoutbox.TransactionOutbox;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
/**
 * At the moment, if any work fails first time, it won't be retried.
 * All we need to add is a background thread that repeatedly calls TransactionOutbox.flush() to pick up and reprocess stale work.
 *
 * How you do this is up to you; it very much depends on how background processing works in your application
 * (a reactive solution will be very different to one based on Guava Service, for example).
 * However, here is a simple example:
 */
public class TransactionalOutboxBackgroundWorker {

  private final TransactionOutbox transactionOutbox;

  @Scheduled(cron = "${outbox.workerCronExpression}")
  public void flushTransactionOutbox() {
    log.trace("Beginning transaction-outbox flush");
    try {
      log.trace("transaction-outbox flush starting");
      transactionOutbox.flush();
      log.trace("transaction-outbox flush completed.");
    } catch (Exception exception) {
      log.warn("Error performing transaction-outbox flush. It will be tried again later.", exception);
    }
    log.trace("End transaction-outbox flush");
  }
}
