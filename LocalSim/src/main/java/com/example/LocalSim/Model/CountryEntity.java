package com.example.LocalSim.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class CountryEntity extends AbstractEntity {

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "number_of_days_to_issue_sim")
    private Integer numberOfDaysToIssueSim;




}
