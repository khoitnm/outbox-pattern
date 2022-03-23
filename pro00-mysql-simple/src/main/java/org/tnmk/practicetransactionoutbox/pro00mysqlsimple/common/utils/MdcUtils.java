package org.tnmk.practicetransactionoutbox.pro00mysqlsimple.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.MDC;

public class MdcUtils {
  public static void putValueIfNotBlank(String key, String value) {
    if (StringUtils.isNotBlank(value)) {
      MDC.put(key, value);
    }
  }
}
