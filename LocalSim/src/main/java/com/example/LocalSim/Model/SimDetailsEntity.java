package com.example.LocalSim.Model;

import com.example.LocalSim.Enum.CompanyNames;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sim_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SimDetailsEntity extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "company_name")
    private CompanyNames companyName;

    @Column(name = "number_of_days")
    private Integer numberOfDays;

    @Column(name = "package_price")
    private Long price;

    @Column(name = "package_details")
    private String packageDetails;

    @Column(name="is_data_available")
    private Boolean isDataAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private CountryEntity country;


}
