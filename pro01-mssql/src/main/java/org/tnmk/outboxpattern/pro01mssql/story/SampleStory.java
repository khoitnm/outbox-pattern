package org.tnmk.outboxpattern.pro01mssql.story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tnmk.outboxpattern.pro01mssql.entity.SampleEntity;
import org.tnmk.outboxpattern.pro01mssql.repository.SampleRepository;

import java.util.Optional;

@Service
public class SampleStory {

  @Autowired
  private SampleRepository sampleRepository;

  public SampleEntity create(SampleEntity sampleEntity) {
    return sampleRepository.save(sampleEntity);
  }

  public SampleEntity update(SampleEntity sampleEntity) {
    if (sampleEntity.getId() == null) {
      throw new IllegalArgumentException("you cannot update an entity with null id.");
    }
    return sampleRepository.save(sampleEntity);
  }

  public Optional<SampleEntity> findById(long id) {
    return sampleRepository.findById(id);
  }
}