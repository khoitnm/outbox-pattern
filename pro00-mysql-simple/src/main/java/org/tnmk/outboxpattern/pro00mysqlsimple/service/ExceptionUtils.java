package org.tnmk.outboxpattern.pro00mysqlsimple.service;

public interface ExceptionUtils {
  static void throwException() {
    throw new NullPointerException("Some Exception");
  }
}
