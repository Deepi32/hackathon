package com.example.LocalSim.Model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer_document")
public class CustomerDocumentEnity extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    CustomerDetailsEntity customerDetailsEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    DocumentEntity documentEntity;

    @Column(name = "document_saved_name")
    String documentSavedName;




}
