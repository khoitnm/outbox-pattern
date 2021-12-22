package org.tnmk.outboxpattern.pro00mysqlsimple.samplebusiness;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tnmk.outboxpattern.pro00mysqlsimple.datafactory.SampleEntityFactory;
import org.tnmk.outboxpattern.pro00mysqlsimple.entity.SampleEntity;
import org.tnmk.outboxpattern.pro00mysqlsimple.service.SampleService;
import org.tnmk.outboxpattern.pro00mysqlsimple.testinfra.BaseSpringTest;

import java.util.Optional;

public class SampleBusinessTest extends BaseSpringTest {
  @Autowired
  private SampleService sampleService;

  @Test
  public void test() {
    SampleEntity sampleEntity = SampleEntityFactory.random();
    SampleEntity savedSampleEntity = sampleService.create(sampleEntity);

    Optional<SampleEntity> sampleEntityOptional = sampleService.findById(savedSampleEntity.getId());
    Assert.assertTrue(sampleEntityOptional.isPresent());
  }
}
