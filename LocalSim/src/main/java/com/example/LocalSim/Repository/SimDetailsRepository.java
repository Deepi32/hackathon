package com.example.LocalSim.Repository;

import com.example.LocalSim.Model.CountryEntity;
import com.example.LocalSim.Model.SimDetailsEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimDetailsRepository extends JpaRepository<SimDetailsEntity, Integer> {

    List<SimDetailsEntity> findAllByCountry(CountryEntity countryEntity);
}
