package org.tnmk.outboxpattern.pro00mysqlsimple.common.outbox.outbox_config;

import com.gruelbox.transactionoutbox.Submitter;
import com.gruelbox.transactionoutbox.TransactionOutboxEntry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.tnmk.outboxpattern.pro00mysqlsimple.common.outbox.OutboxRepository;

import java.util.function.Consumer;

@Component
@Slf4j
@RequiredArgsConstructor
public class TransactionalOutboxSubmitter implements Submitter {

  private final OutboxRepository outboxRepository;

  @Override public void submit(TransactionOutboxEntry entry, Consumer<TransactionOutboxEntry> consumer) {
    log.info("OutboxSubmitter.submit(): start {}", entry);
    consumer.accept(entry);

    // Only delete if success. Otherwise, just keep outbox entry so that it could be retried.
    if (entry.isProcessed()) {
      try {
        log.info("OutboxSubmitter.submit(): delete entryId {}", entry.getId());
        outboxRepository.deleteById(entry.getId());
      } catch (Exception ex) {
        log.warn("OutboxSubmitter.submit(): cannot delete entryId {}. "
            + "However, the process will be continued and it will be cleaned up in the next scheduled run of TransactionalOutboxWorker."
            + "Root cause: {}", entry.getId(), ex.getMessage(), ex);
      }
    }
    log.info("OutboxSubmitter.submit(): end {}", entry);

  }
}
