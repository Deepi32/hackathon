package com.example.LocalSim.Response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DocumentResponse {

    List<DocumentR> documentRList;
    Boolean isOnlineVisa;

}
