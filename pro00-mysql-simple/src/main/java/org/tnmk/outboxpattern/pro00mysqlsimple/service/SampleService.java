package org.tnmk.outboxpattern.pro00mysqlsimple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tnmk.outboxpattern.pro00mysqlsimple.entity.SampleEntity;
import org.tnmk.outboxpattern.pro00mysqlsimple.repository.SampleRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SampleService {

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

  @Transactional
  public List<SampleEntity> createAndUpdateMany(SampleEntity... entities) {
    List<SampleEntity> result = new ArrayList<>();
    for (SampleEntity entity : entities) {
      entity = create(entity);

      entity.setName(entity.getName() + "Updated");
      entity = update(entity);
      result.add(entity);
    }
    return result;
  }

  public Optional<SampleEntity> findById(long id) {
    return sampleRepository.findById(id);
  }
}
