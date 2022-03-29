package org.tnmk.practicetransactionoutbox.pro00mysqlsimple.common.outbox.outbox_context;

import com.gruelbox.transactionoutbox.TransactionOutboxEntry;

public class TransactionalOutboxEntryContextHolder {

  private static final ThreadLocal<TransactionOutboxEntry> contextHolder = new ThreadLocal();

  public static TransactionOutboxEntry getContext() {
    return contextHolder.get();
  }

  public static void setContext(TransactionOutboxEntry context) {
    contextHolder.set(context);
  }

  public static void clearContext() {
    contextHolder.remove();
  }
}
