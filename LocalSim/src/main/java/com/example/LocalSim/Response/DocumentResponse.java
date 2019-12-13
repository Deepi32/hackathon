package com.example.LocalSim.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class DocumentResponse {

    List<DocumentR> documentRList;

    Boolean isOnlineVisa;

}
