package org.tnmk.practicetransactionoutbox.pro00mysqlsimple.common.outbox.outbox_scheduled_log;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.tnmk.practicetransactionoutbox.pro00mysqlsimple.common.outbox.OutboxRepository;

import java.util.List;

/**
 * This is just for help debugging based on log messages:
 * Every second, it will print the current outboxIds in the DB.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OutboxScheduledLogJob {
  private final OutboxRepository outboxRepository;

  @Scheduled(cron = "* * * * * *")
  public void traceOutbox() {
    List<String> outboxIds = outboxRepository.findOutboxIds();
    if (!outboxIds.isEmpty()) {
      log.info("Outbox events: " + outboxIds);
    }
  }
}
