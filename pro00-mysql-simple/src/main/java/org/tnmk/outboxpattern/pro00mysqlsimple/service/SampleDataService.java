package org.tnmk.outboxpattern.pro00mysqlsimple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tnmk.outboxpattern.pro00mysqlsimple.entity.SampleEntity;
import org.tnmk.outboxpattern.pro00mysqlsimple.repository.SampleRepository;

@Service
public class SampleDataService {

  @Autowired
  private SampleRepository sampleRepository;

  public SampleEntity create(SampleEntity sampleEntity) {
    return sampleRepository.save(sampleEntity);
  }
}
