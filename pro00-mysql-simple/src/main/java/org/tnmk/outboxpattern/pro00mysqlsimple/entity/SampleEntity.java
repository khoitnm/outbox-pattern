package org.tnmk.outboxpattern.pro00mysqlsimple.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.time.ZonedDateTime;

@Entity
@Table(name = "sample_entity", catalog = "outbox_pattern_db")
@Data
public class SampleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "sample_entity_id")
  private Long id;

  private String name;
//
//  @Column(name = "starting_date_time", columnDefinition = "TIMESTAMP(6)")
//  private ZonedDateTime startingDateTime;
//  /**
//   * The columnDefinition here is specific for MY SQL Server only. It won't work with other kinds of DBs such as DB2, Oracle.
//   */
//  @Column(name = "creation_dateTime", updatable = false, columnDefinition = "TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6)")
//  @CreationTimestamp
//  private Instant createdDateTime;
//
//  @Column(name = "update_dateTime")
//  @UpdateTimestamp
//  private ZonedDateTime updateDateTime;
}
