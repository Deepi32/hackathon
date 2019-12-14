package com.example.LocalSim.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponse {

    String destinationFrom;
    String destinationTo;
    String countryFrom;
    String countryTo;
    String bookingId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    Integer userId;
}
