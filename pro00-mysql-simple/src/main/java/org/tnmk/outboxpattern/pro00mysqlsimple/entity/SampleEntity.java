package org.tnmk.outboxpattern.pro00mysqlsimple.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "sample_entity", catalog = "outbox_pattern_db")
@Data
public class SampleEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "sample_entity_id")
  private Long id;

  private String name;
}
