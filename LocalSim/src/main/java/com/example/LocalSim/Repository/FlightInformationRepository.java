package com.example.LocalSim.Repository;

import com.example.LocalSim.Model.FlightInformationEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightInformationRepository extends JpaRepository<FlightInformationEntity,Integer> {

    Optional<FlightInformationEntity> findByPnrNo(String pnrNo);
}
