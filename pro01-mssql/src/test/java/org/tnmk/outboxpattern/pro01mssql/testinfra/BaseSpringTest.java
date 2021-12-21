package org.tnmk.outboxpattern.pro01mssql.testinfra;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.tnmk.outboxpattern.pro01mssql.Pro01MssqlApplication;
import org.tnmk.outboxpattern.pro01mssql.testinfra.dbtestcontainer.DBContainerContextInitializer;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Pro01MssqlApplication.class})
@Ignore
public abstract class BaseSpringTest {
}
