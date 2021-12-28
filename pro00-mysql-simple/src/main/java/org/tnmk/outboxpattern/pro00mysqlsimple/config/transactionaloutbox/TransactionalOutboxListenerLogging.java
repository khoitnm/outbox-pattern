package org.tnmk.outboxpattern.pro00mysqlsimple.config.transactionaloutbox;

import com.gruelbox.transactionoutbox.TransactionOutboxEntry;
import com.gruelbox.transactionoutbox.TransactionOutboxListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransactionalOutboxListenerLogging implements TransactionOutboxListener {
  @Override
  public void scheduled(TransactionOutboxEntry entry) {
    log.info("Outbox scheduled {}", entry);
  }

  @Override
  public void success(TransactionOutboxEntry entry) {
    log.info("Outbox Success {}", entry);
  }

  @Override
  public void blocked(TransactionOutboxEntry entry, Throwable cause) {
    log.info("Outbox blocked {} because of error {}", entry, cause.getMessage());
  }
}
