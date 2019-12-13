package com.example.LocalSim.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "flight_information")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FlightInformationEntity extends AbstractEntity {

  @Column(name = "destination_from")
  private String destinationFrom;

  @Column(name = "destination_to")
  private String destinationTo;


  @ManyToOne(fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @JoinColumn(name = "country_from")
  private CountryEntity countryFrom;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "country_to")
  private CountryEntity countryTo;

  @Column(name="Start_time")
  private LocalDateTime startTime;

  @Column(name="end_time")
  private LocalDateTime endTime;

  @Column(name = "pnr_no")
  private String pnrNo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private UserEntity user;


}
