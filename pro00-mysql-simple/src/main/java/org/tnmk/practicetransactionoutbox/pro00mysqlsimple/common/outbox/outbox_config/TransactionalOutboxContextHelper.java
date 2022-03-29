package org.tnmk.practicetransactionoutbox.pro00mysqlsimple.common.outbox.outbox_config;

import com.gruelbox.transactionoutbox.TransactionOutboxEntry;
import org.slf4j.MDC;
import org.tnmk.practicetransactionoutbox.pro00mysqlsimple.common.outbox.outbox_context.TransactionalOutboxEntryContextHolder;
import org.tnmk.practicetransactionoutbox.pro00mysqlsimple.common.utils.MdcUtils;
import org.tnmk.practicetransactionoutbox.pro00mysqlsimple.samplebusiness.service.MdcConstants;

public class TransactionalOutboxContextHelper {

  public static void addEntryInfoToMdcContext(TransactionOutboxEntry entry) {
    TransactionalOutboxEntryContextHolder.setContext(entry);

    MdcUtils.putValueIfNotBlank(MdcConstants.OUTBOX_ID, entry.getId());
    MdcUtils.putValueIfNotBlank(MdcConstants.OUTBOX_UNIQUE_REQUEST_ID, entry.getUniqueRequestId());
    MdcUtils.putValueIfNotBlank(MdcConstants.OUTBOX_CLASS, entry.getInvocation().getClassName());
    MdcUtils.putValueIfNotBlank(MdcConstants.OUTBOX_METHOD, entry.getInvocation().getMethodName());
  }

  public static void removeEntryIdFromMdcContext() {
    TransactionalOutboxEntryContextHolder.clearContext();

    MDC.remove(MdcConstants.OUTBOX_UNIQUE_REQUEST_ID);
    MDC.remove(MdcConstants.OUTBOX_ID);
    MDC.remove(MdcConstants.OUTBOX_CLASS);
    MDC.remove(MdcConstants.OUTBOX_METHOD);
  }
}
