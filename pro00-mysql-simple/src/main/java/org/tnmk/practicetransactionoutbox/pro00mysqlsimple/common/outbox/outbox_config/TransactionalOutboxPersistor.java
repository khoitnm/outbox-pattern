package org.tnmk.practicetransactionoutbox.pro00mysqlsimple.common.outbox.outbox_config;

import com.gruelbox.transactionoutbox.AlreadyScheduledException;
import com.gruelbox.transactionoutbox.DefaultPersistor;
import com.gruelbox.transactionoutbox.Invocation;
import com.gruelbox.transactionoutbox.Transaction;
import com.gruelbox.transactionoutbox.TransactionOutboxEntry;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Slf4j
@SuperBuilder
public class TransactionalOutboxPersistor extends DefaultPersistor {

  @Override
  public void save(Transaction tx, TransactionOutboxEntry entry) throws SQLException, AlreadyScheduledException {
    TransactionalOutboxContextHelper.addEntryInfoToMdcContext(entry);
    log.info("Save entryId {}", entry.getId());
    super.save(tx, entry);
  }
}
