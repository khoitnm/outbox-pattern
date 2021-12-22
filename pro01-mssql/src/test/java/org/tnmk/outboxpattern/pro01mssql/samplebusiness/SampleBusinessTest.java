package org.tnmk.outboxpattern.pro01mssql.samplebusiness;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tnmk.outboxpattern.pro01mssql.datafactory.SampleEntityFactory;
import org.tnmk.outboxpattern.pro01mssql.entity.SampleEntity;
import org.tnmk.outboxpattern.pro01mssql.story.SampleStory;
import org.tnmk.outboxpattern.pro01mssql.testinfra.BaseSpringTest;
import org.tnmk.outboxpattern.pro01mssql.testinfra.BaseSpringTest_WithTestContainer;

import java.util.Optional;

public class SampleBusinessTest extends BaseSpringTest {
  @Autowired
  private SampleStory sampleStory;

  @Test
  public void test() {
    SampleEntity sampleEntity = SampleEntityFactory.constructSampleEntity();
    SampleEntity savedSampleEntity = sampleStory.create(sampleEntity);

    Optional<SampleEntity> sampleEntityOptional = sampleStory.findById(savedSampleEntity.getId());
    Assert.assertTrue(sampleEntityOptional.isPresent());
  }
}
