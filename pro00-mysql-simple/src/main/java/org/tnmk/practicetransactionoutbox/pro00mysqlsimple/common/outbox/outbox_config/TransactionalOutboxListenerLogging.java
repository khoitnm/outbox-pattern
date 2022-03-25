package org.tnmk.practicetransactionoutbox.pro00mysqlsimple.common.outbox.outbox_config;

import com.gruelbox.transactionoutbox.TransactionOutboxEntry;
import com.gruelbox.transactionoutbox.TransactionOutboxListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static org.tnmk.practicetransactionoutbox.pro00mysqlsimple.common.outbox.outbox_config.OutboxMdcHelper.addEntryInfoToMdcContext;
import static org.tnmk.practicetransactionoutbox.pro00mysqlsimple.common.outbox.outbox_config.OutboxMdcHelper.removeEntryIdFromMdcContext;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionalOutboxListenerLogging implements TransactionOutboxListener {
  @Override
  public void scheduled(TransactionOutboxEntry entry) {
//    addEntryInfoToMdcContext(entry); // This doesn't help to copy MDC into the actual outboxJob, also not copy to Submitter!!!, really!!!
    log.info("OutboxListener.scheduled: {}", entry);
  }

  @Override
  public void success(TransactionOutboxEntry entry) {
    log.info("OutboxListener.success: {}", entry);
//    removeEntryIdFromMdcContext();
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
