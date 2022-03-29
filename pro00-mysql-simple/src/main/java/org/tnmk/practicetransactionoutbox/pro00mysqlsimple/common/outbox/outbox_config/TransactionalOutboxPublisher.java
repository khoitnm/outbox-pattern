package org.tnmk.practicetransactionoutbox.pro00mysqlsimple.common.outbox.outbox_config;

import com.gruelbox.transactionoutbox.AlreadyScheduledException;
import com.gruelbox.transactionoutbox.TransactionOutbox;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.stereotype.Service;
import org.tnmk.practicetransactionoutbox.pro00mysqlsimple.samplebusiness.service.MdcConstants;

import java.util.function.Consumer;

/**
 * <b>IMPORTANT.</b>
 * <ol>
 *   <li>
 *     All jobs <b>must</b> be idempotent because the guarantee is at least once successful
 *     execution, and not only once successful execution.
 *   </li>
 *   <li>
 *     For backwards compatibility, the signature of existing jobs should <b>never</b> be modified.
 *     The <b>entire</b> method invocation signature of whatever you pass to
 *     {@link TransactionOutbox#schedule(Class)} will be JSON <b>serialized</b> to the DB.
 *     This includes:
 *     <ul>
 *       <li>class name</li>
 *       <li>method name</li>
 *       <li>method parameter types</li>
 *       <li>method parameter order</li>
 *       <li>method argument values</li>
 *     </ul>
 *   </li>
 * </ol>
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionalOutboxPublisher {
  private final TransactionOutbox transactionOutbox;

  /**
   * @throws AlreadyScheduledException when there's is another outbox event with the same uniqueRequestId is already exist in DB.
   *  So this exception can help client code to be flexible:
   *  - They can decide whether they can ignore this exception when it happens (by catching it)
   *  - Or they can rollback the whole transaction. It totally in the control of client code.
   */
  @SneakyThrows
  public void withUniqueRequestId(
      @NonNull String uniqueRequestId,
      Consumer<TransactionOutbox.ParameterizedScheduleBuilder> consumer) throws AlreadyScheduledException {
    try {
      log.debug("Publish outbox event with uniqueRequestId '{}'", uniqueRequestId);
//      MDC.put(MdcConstants.OUTBOX_UNIQUE_REQUEST_ID, uniqueRequestId);
      consumer.accept(transactionOutbox.with().uniqueRequestId(uniqueRequestId));
    } finally {
      MDC.remove(MdcConstants.OUTBOX_UNIQUE_REQUEST_ID);
    }
  }
}
