package org.tnmk.outboxpattern.pro00mysqlsimple.service;

import com.gruelbox.transactionoutbox.TransactionOutbox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tnmk.outboxpattern.pro00mysqlsimple.datafactory.SampleEntityFactory;
import org.tnmk.outboxpattern.pro00mysqlsimple.entity.SampleEntity;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleWithOutboxTransactionService {
  private final TransactionOutbox transactionOutbox;

  @Transactional
  public List<SampleEntity> executeWithOutbox(String outboxUniqueId) {
    SampleEntity sampleEntity01 = SampleEntityFactory.random();
    SampleEntity sampleEntity02 = SampleEntityFactory.random();

    return transactionOutbox.with()
        .uniqueRequestId(outboxUniqueId)
        .schedule(SampleService.class)
        .createAndUpdateMany(sampleEntity01, sampleEntity02);
  }
}
