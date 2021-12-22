package org.tnmk.outboxpattern.pro00mysqlsimple.datafactory;

import org.tnmk.outboxpattern.pro00mysqlsimple.entity.SampleEntity;

import java.time.ZonedDateTime;

public class SampleEntityFactory {
  public static SampleEntity constructSampleEntity() {
    SampleEntity sampleEntity = new SampleEntity();
    sampleEntity.setName("Sample_" + System.nanoTime());
//    sampleEntity.setStartingDateTime(ZonedDateTime.now());
    return sampleEntity;
  }
}
