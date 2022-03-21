package org.tnmk.outboxpattern.pro00mysqlsimple.common.outbox;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OutboxRepository {
  private final JdbcTemplate jdbcTemplate;

  public List<String> findOutboxIds() {
    List<String> result = jdbcTemplate.queryForList("select id from TXNO_OUTBOX", String.class);
    return result;
  }

  /**
   * @param outboxId this is the id of the outbox entry. Note: it's different from uniqueRequestId.
   */
  public void deleteById(String outboxId) {
    jdbcTemplate.execute("delete from TXNO_OUTBOX where id = '" + outboxId + "'");
  }
}
