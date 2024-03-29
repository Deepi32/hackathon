package com.example.LocalSim.Controller;

import com.example.LocalSim.Service.CustomerSimService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RestController
public class CustomerSimController {

  @Autowired
  CustomerSimService customerSimService;

  @GetMapping("flight")
  public ResponseEntity<?> getFlightInformation(@RequestParam(name = "bookingId") String bookingId) {

    return ResponseEntity.ok(customerSimService.getFlightInformation(bookingId));
  }

  @GetMapping("sim/details")
  public ResponseEntity<?> getSimDetailsFromCountry(@RequestParam(name = "countryName") String countryName)
  {
    return ResponseEntity.ok(customerSimService.getSimInformationFromCountry(countryName));
  }

  @PostMapping(value = "/upload/documents",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> uploadDocuments(@RequestParam List<MultipartFile> files,
                                           @NotNull @RequestParam("customerId") Integer customerId, HttpServletRequest servletRequest) throws IOException {
    return ResponseEntity.ok(customerSimService.uploadDocumentsInFolder(files,servletRequest,customerId));


  }

  @GetMapping("document/details")
  public ResponseEntity<?> showDocuments(@NotNull @RequestParam("simId") Integer simId,
                                         @RequestParam(name = "bookingId") String bookingId)
  {

    return ResponseEntity.ok(customerSimService.showDocuments(simId,bookingId));
  }
  @GetMapping("payment")
  public ResponseEntity<?> paymentPaid(@NotNull @RequestParam("customerId") Integer customerId)
  {
    return ResponseEntity.ok(customerSimService.paymentPaid(customerId));

  }

  @PostMapping(value = "save/documents")
  public ResponseEntity<?> saveDocuments(@NotNull @RequestParam("customerId") Integer customerId, @RequestParam("isOnArrivalVisa") boolean isOnArrivalVisa) {

    return ResponseEntity.ok(customerSimService.saveDocuments(customerId, isOnArrivalVisa));

  }

  @GetMapping(value = "result")
  public ResponseEntity<?> renderResult(@NotNull @RequestParam("customerId") Integer customerId) {
    return ResponseEntity.ok(customerSimService.renderResult(customerId));
  }

}
