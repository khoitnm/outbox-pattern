package org.tnmk.practicetransactionoutbox.pro00mysqlsimple.samplebusiness.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "sample_entity", catalog = "practice_transaction_outbox_db")
@Data
public class SampleEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "sample_entity_id")
  private Long id;

  private String name;
}
