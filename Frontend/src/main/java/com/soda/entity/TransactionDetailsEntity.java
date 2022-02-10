package com.soda.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction_details")
public class TransactionDetailsEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 9076080979938330843L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;


  @ManyToOne(optional = false)
  @JoinColumn(name = "soda_detail_id", nullable = false)
  private SodaDetailsEntity sodaDetailsEntity;

  @Column(name = "despence_date")
  private LocalDateTime despenceDate;


  public TransactionDetailsEntity(SodaDetailsEntity sodaDetailsEntity, LocalDateTime despenceDate) {
    this.sodaDetailsEntity = sodaDetailsEntity;
    this.despenceDate = despenceDate;
  }



}
