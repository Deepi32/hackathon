package com.example.LocalSim.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentR {
    Integer id;
    String name;
    String information;
}
