package com.example.LocalSim.Model;



import com.example.LocalSim.Enum.PaymentStatus;
import lombok.*;

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
@Table(name = "customer_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class CustomerDetailsEntity extends AbstractEntity {

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "flight_id")
  private FlightInformationEntity flightInformationEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sim_id")
  private SimDetailsEntity simDetails;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "destination_to")
  private CountryEntity destinationTo;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_status")
  private PaymentStatus paymentStatus;

  @Column(name="payment_amount")
  private String paymentAmount;

  @Column(name = "is_document_upload")
  private Boolean isDocumentUpload;

  @Column(name = "is_on_arrival_visa")
  private Boolean isOnArrivalVisa;

  @Column(name="verification_code")
  private String verificationCode;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="user_id")
  private UserEntity userEntity;
}
