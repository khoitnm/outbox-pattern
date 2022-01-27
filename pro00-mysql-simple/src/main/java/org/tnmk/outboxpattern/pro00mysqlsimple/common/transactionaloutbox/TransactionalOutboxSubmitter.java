package org.tnmk.outboxpattern.pro00mysqlsimple.common.transactionaloutbox;

import com.gruelbox.transactionoutbox.Submitter;
import com.gruelbox.transactionoutbox.TransactionOutboxEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class TransactionalOutboxSubmitter implements Submitter {
  @Override public void submit(TransactionOutboxEntry entry, Consumer<TransactionOutboxEntry> consumer) {
    log.info("OUTBOX SUBMIT: start {}", entry);
    consumer.accept(entry);
    log.info("OUTBOX SUBMIT: end {}", entry);
  }
}
