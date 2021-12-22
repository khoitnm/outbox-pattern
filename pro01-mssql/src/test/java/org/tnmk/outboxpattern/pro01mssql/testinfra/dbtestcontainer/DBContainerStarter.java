package org.tnmk.outboxpattern.pro01mssql.testinfra.dbtestcontainer;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.wait.strategy.WaitAllStrategy;
import org.testcontainers.containers.wait.strategy.WaitStrategy;

import java.time.Duration;

public class DBContainerStarter {
  public static final MySQLContainer DB_CONTAINER = startContainer();

  private static MySQLContainer startContainer() {
    MySQLContainer container = new MySQLContainer();
    container
        .withStartupTimeout(Duration.ofSeconds(90))
        .start();
    container.start();
    return container;
  }

}
