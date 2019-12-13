package com.example.LocalSim.Repository;

import com.example.LocalSim.Model.CustomerSimDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerSimRepository extends JpaRepository<CustomerSimDetailsEntity,Integer> {

    Optional<CustomerSimDetailsEntity> findById(Integer id);
}
