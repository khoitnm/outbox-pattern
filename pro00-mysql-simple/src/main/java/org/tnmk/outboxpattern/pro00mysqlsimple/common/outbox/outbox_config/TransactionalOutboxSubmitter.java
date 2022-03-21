package org.tnmk.outboxpattern.pro00mysqlsimple.common.outbox.outbox_config;

import com.gruelbox.transactionoutbox.Submitter;
import com.gruelbox.transactionoutbox.TransactionOutboxEntry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;
import org.tnmk.outboxpattern.pro00mysqlsimple.common.outbox.OutboxRepository;
import org.tnmk.outboxpattern.pro00mysqlsimple.common.utils.MdcUtils;
import org.tnmk.outboxpattern.pro00mysqlsimple.samplebusiness.service.MdcConstant;

import java.util.function.Consumer;

@Component
@Slf4j
@RequiredArgsConstructor
public class TransactionalOutboxSubmitter implements Submitter {

  private final OutboxRepository outboxRepository;

  @Override public void submit(TransactionOutboxEntry entry, Consumer<TransactionOutboxEntry> consumer) {
    try {
      addEntryIdToMdcContext(entry);
      log.info("OutboxSubmitter.submit(): start {}", entry);
      consumer.accept(entry);

      removeEntryIfSucceed(entry);

      log.info("OutboxSubmitter.submit(): end {}", entry);
    } finally {
      removeEntryIdFromMdcContext();
    }

  }

  private void removeEntryIfSucceed(TransactionOutboxEntry entry) {
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
  }

  private void addEntryIdToMdcContext(TransactionOutboxEntry entry) {
    MdcUtils.putValueIfNotBlank(MdcConstant.OUTBOX_ID, entry.getId());
    MdcUtils.putValueIfNotBlank(MdcConstant.OUTBOX_UNIQUE_REQUEST_ID, entry.getUniqueRequestId());
    MdcUtils.putValueIfNotBlank(MdcConstant.OUTBOX_CLASS, entry.getInvocation().getClassName());
    MdcUtils.putValueIfNotBlank(MdcConstant.OUTBOX_METHOD, entry.getInvocation().getMethodName());
  }

  private void removeEntryIdFromMdcContext() {
    MDC.remove(MdcConstant.OUTBOX_UNIQUE_REQUEST_ID);
    MDC.remove(MdcConstant.OUTBOX_ID);
    MDC.remove(MdcConstant.OUTBOX_CLASS);
    MDC.remove(MdcConstant.OUTBOX_METHOD);
  }
}
