package org.tnmk.outboxpattern.pro00mysqlsimple.common.outbox_config;

import com.gruelbox.transactionoutbox.Submitter;
import com.gruelbox.transactionoutbox.TransactionOutboxEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class TransactionalOutboxSubmitter implements Submitter {
  @Override public void submit(TransactionOutboxEntry entry, Consumer<TransactionOutboxEntry> consumer) {
    log.info("OutboxSubmitter.submit(): start {}", entry);
    consumer.accept(entry);
    log.info("OutboxSubmitter.submit(): end {}", entry);
  }
}
