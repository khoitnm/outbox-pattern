package org.tnmk.outboxpattern.pro00mysqlsimple.common.transactionaloutbox;

import com.gruelbox.transactionoutbox.TransactionOutbox;
import com.gruelbox.transactionoutbox.TransactionOutboxEntry;
import com.gruelbox.transactionoutbox.TransactionOutboxListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionalOutboxListenerLogging implements TransactionOutboxListener {
  @Override
  public void scheduled(TransactionOutboxEntry entry) {
    log.info("OutboxListener.scheduled: {}", entry);
  }

  @Override
  public void success(TransactionOutboxEntry entry) {
    log.info("OutboxListener.success: {}", entry);
  }

  @Override
  public void failure(TransactionOutboxEntry entry, Throwable cause) {
    log.info("OutboxListener.failure: {}", entry);
  }

  @Override
  public void blocked(TransactionOutboxEntry entry, Throwable cause) {
    log.info("OutboxListener.blocked: {} because of error {}", entry, cause.getMessage());
  }
}
