package com.example.LocalSim.Repository;

import com.example.LocalSim.Model.CustomerDetailsEntity;
import com.example.LocalSim.Model.CustomerDocumentEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDocumentRepository extends JpaRepository<CustomerDocumentEnity,Integer> {

}
