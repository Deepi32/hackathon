package com.example.LocalSim.Model;



import com.example.LocalSim.Enum.PaymentStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer_sim_details")
public class CustomerSimDetailsEntity extends AbstractEntity {

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "flight_id")
  private FlightInformationEntity flightInformationEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sim_id")
  private SimDetailsEntity simDetails;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_status")
  private PaymentStatus paymentStatus;

  @Column(name = "is_document_upload")
  private Boolean isDocumentUpload;
}
