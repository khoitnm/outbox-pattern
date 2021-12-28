package org.tnmk.outboxpattern.pro00mysqlsimple.datafactory;

import org.tnmk.outboxpattern.pro00mysqlsimple.entity.SampleEntity;

public class SampleEntityFactory {
  public static SampleEntity random() {
    return withName("Sample_" + System.nanoTime());
  }

  public static SampleEntity withName(String name) {
    SampleEntity sampleEntity = new SampleEntity();
    sampleEntity.setName(name);
    return sampleEntity;
  }
}
