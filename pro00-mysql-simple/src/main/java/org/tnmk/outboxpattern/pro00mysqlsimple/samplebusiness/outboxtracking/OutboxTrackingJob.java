package org.tnmk.outboxpattern.pro00mysqlsimple.samplebusiness.outboxtracking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is just for help debugging based on log messages.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OutboxTrackingJob {
  private final OutboxRepository outboxRepository;

  @Scheduled(cron = "* * * * * *")
  public void traceOutbox() {
    List<String> outboxIds = outboxRepository.findOutboxIds();
    if (!outboxIds.isEmpty()) {
      log.info("Outbox events: " + outboxIds);
    }
  }
}
