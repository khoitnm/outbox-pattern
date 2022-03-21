package org.tnmk.outboxpattern.pro00mysqlsimple.samplebusiness.service;

public interface ExceptionHelper {
  static void throwAnException() {
    throw new NullPointerException("Some Exception");
  }
}
