package org.tnmk.outboxpattern.pro01mssql.config.transactionaloutbox;

import com.gruelbox.transactionoutbox.Dialect;
import com.gruelbox.transactionoutbox.Persistor;
import com.gruelbox.transactionoutbox.SpringInstantiator;
import com.gruelbox.transactionoutbox.SpringTransactionManager;
import com.gruelbox.transactionoutbox.SpringTransactionOutboxConfiguration;
import com.gruelbox.transactionoutbox.TransactionOutbox;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

@Import({ SpringTransactionOutboxConfiguration.class }) // This is required for SpringTransactionManager & SpringInstantiator beans
@Configuration
public class TransactionalOutboxConfig {
  @Bean
  @Lazy
  public TransactionOutbox transactionOutbox(SpringTransactionManager springTransactionManager, SpringInstantiator springInstantiator) {
    return TransactionOutbox.builder()
        .instantiator(springInstantiator)
        .transactionManager(springTransactionManager)
        .persistor(Persistor.forDialect(Dialect.MY_SQL_8))
        .build();
    //
    //    return TransactionOutbox.builder()
    //        .attemptFrequency(transactionalOutboxProperties.getRetrySleepPeriod())
    //        .blockAfterAttempts(transactionalOutboxProperties.getRetryLimit())
    //        .initializeImmediately(true)
    //        .instantiator(springInstantiator)
    //        .listener(transactionOutboxListener)
    //        .submitter(transactionOutboxSubmitter)
    //        .persistor(transactionOutboxPersistor)
    //        .retentionThreshold(transactionalOutboxProperties.getRetentionPeriod())
    //        .serializeMdc(true)
    //        .transactionManager(springTransactionManager)
    //        .build();
  }
}
