package org.tnmk.outboxpattern.pro00mysqlsimple.samplebusiness;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tnmk.outboxpattern.pro00mysqlsimple.datafactory.SampleEntityFactory;
import org.tnmk.outboxpattern.pro00mysqlsimple.entity.SampleEntity;
import org.tnmk.outboxpattern.pro00mysqlsimple.repository.SampleRepository;
import org.tnmk.outboxpattern.pro00mysqlsimple.service.SampleDataService;
import org.tnmk.outboxpattern.pro00mysqlsimple.testinfra.BaseSpringTest;

public class SampleBusinessTest extends BaseSpringTest {
  @Autowired
  private SampleDataService sampleDataService;

  @Autowired
  private SampleRepository sampleRepository;

  @Test
  public void test() {
    SampleEntity sampleEntity = SampleEntityFactory.random();
    SampleEntity savedSampleEntity = sampleDataService.create(sampleEntity);
    Assert.assertTrue(sampleRepository.findById(savedSampleEntity.getId()).isPresent());
  }
}
