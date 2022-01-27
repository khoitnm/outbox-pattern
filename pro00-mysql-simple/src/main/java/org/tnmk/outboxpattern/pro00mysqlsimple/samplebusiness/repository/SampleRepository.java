package org.tnmk.outboxpattern.pro00mysqlsimple.samplebusiness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tnmk.outboxpattern.pro00mysqlsimple.samplebusiness.entity.SampleEntity;

public interface SampleRepository extends JpaRepository<SampleEntity, Long> {
}
