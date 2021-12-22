package org.tnmk.outboxpattern.pro00mysqlsimple.config.transactionaloutbox;

import com.gruelbox.transactionoutbox.DefaultInvocationSerializer;
import com.gruelbox.transactionoutbox.DefaultPersistor;
import com.gruelbox.transactionoutbox.Dialect;
import com.gruelbox.transactionoutbox.Persistor;
import com.gruelbox.transactionoutbox.SpringInstantiator;
import com.gruelbox.transactionoutbox.SpringTransactionManager;
import com.gruelbox.transactionoutbox.SpringTransactionOutboxConfiguration;
import com.gruelbox.transactionoutbox.TransactionOutbox;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.tnmk.outboxpattern.pro00mysqlsimple.entity.SampleEntity;

import java.time.Duration;
import java.util.Set;
import java.util.UUID;

@Import({ SpringTransactionOutboxConfiguration.class }) // This is required for SpringTransactionManager & SpringInstantiator beans
@Configuration
public class TransactionalOutboxConfig {
  @Bean
  //  @Lazy
  public TransactionOutbox transactionOutbox(
      SpringTransactionManager springTransactionManager, SpringInstantiator springInstantiator, Persistor persistor) {
    return TransactionOutbox.builder()
        .instantiator(springInstantiator)
        .transactionManager(springTransactionManager)
        .retentionThreshold(Duration.ofSeconds(5))
        .persistor(persistor)
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

  @Bean
  public Persistor transactionOutboxPersistor() {
    return DefaultPersistor.builder()
        // This config must match with Dialect config in application.yml: `hibernate.dialect: org.hibernate.dialect.MySQL5InnoDBDialect`
        .dialect(Dialect.MY_SQL_5)
        .migrate(false)  // False because migrations are handled separately
        .serializer(DefaultInvocationSerializer.builder()
            .serializableTypes(Set.of(
                UUID.class,
                // Must define this class here to avoid error "Cannot serialize: Class Not Found SampleEntity.class"
                SampleEntity.class
            ))
            .build())
        .writeLockTimeoutSeconds(1)
        .build();
  }
}
