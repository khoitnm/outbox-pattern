package org.tnmk.outboxpattern.pro00mysqlsimple.config.transactionaloutbox;

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

  /**
   * Scheduled job to poll the transaction-outbox table for tasks to retry.
   *
   * Spring cron expression (which is a bit different from K8s cron expression):
   *  ┌───────────── second (0-59)
   *  │ ┌───────────── minute (0 - 59)
   *  │ │ ┌───────────── hour (0 - 23)
   *  │ │ │ ┌───────────── day of the month (1 - 31)
   *  │ │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
   *  │ │ │ │ │ ┌───────────── day of the week (0 - 7)
   *  │ │ │ │ │ │          (or MON-SUN -- 0 or 7 is Sunday)
   *  │ │ │ │ │ │
   *  * * * * * *
   */
  @Scheduled(cron = "*/15 * * * * *") // every 15 seconds.
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
