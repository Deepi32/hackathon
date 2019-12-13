package com.example.LocalSim.Repository;

import com.example.LocalSim.Model.CountryEntity;
import com.example.LocalSim.Model.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<DocumentEntity,Integer> {
    List<DocumentEntity> findAllByCountry(CountryEntity countryEntity);
}
