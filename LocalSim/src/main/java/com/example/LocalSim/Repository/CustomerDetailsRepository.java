package com.example.LocalSim.Repository;

import com.example.LocalSim.Model.CustomerDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetailsEntity,Integer> {

    Optional<CustomerDetailsEntity> findById(Integer id);
}
