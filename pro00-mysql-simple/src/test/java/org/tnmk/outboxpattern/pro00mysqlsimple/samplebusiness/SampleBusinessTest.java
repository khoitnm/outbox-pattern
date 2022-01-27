package org.tnmk.outboxpattern.pro00mysqlsimple.samplebusiness;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tnmk.outboxpattern.pro00mysqlsimple.samplebusiness.datafactory.SampleEntityFactory;
import org.tnmk.outboxpattern.pro00mysqlsimple.samplebusiness.entity.SampleEntity;
import org.tnmk.outboxpattern.pro00mysqlsimple.samplebusiness.repository.SampleRepository;
import org.tnmk.outboxpattern.pro00mysqlsimple.testinfra.BaseSpringTest;

public class SampleBusinessTest extends BaseSpringTest {

  @Autowired
  private SampleRepository sampleRepository;

  @Test
  public void test() {
    SampleEntity sampleEntity = SampleEntityFactory.random();
    SampleEntity savedSampleEntity = sampleRepository.save(sampleEntity);
    Assert.assertTrue(sampleRepository.findById(savedSampleEntity.getId()).isPresent());
  }
}
