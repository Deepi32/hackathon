package com.example.LocalSim.Repository;

import com.example.LocalSim.Model.CountryEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository  extends JpaRepository<CountryEntity,Integer> {
    Optional<CountryEntity> findByCountryName(String countryName);
}
