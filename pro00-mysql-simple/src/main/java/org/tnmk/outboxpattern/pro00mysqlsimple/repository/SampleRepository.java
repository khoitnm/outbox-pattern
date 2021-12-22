package org.tnmk.outboxpattern.pro00mysqlsimple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tnmk.outboxpattern.pro00mysqlsimple.entity.SampleEntity;

public interface SampleRepository extends JpaRepository<SampleEntity, Long> {
}
