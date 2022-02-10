package com.soda.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "soda_details")
public class SodaDetailsEntity implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 9076080979938330843L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "quantity")
  private Integer quantity;

  @OneToMany(mappedBy = "sodaDetailsEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<TransactionDetailsEntity> transactionDetailsEntities;


}
