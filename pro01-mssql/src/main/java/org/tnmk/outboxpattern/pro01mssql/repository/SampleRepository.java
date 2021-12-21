package org.tnmk.outboxpattern.pro01mssql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tnmk.outboxpattern.pro01mssql.entity.SampleEntity;

public interface SampleRepository extends JpaRepository<SampleEntity, Long> {
}
