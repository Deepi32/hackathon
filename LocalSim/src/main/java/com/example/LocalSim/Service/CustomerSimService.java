package com.example.LocalSim.Service;

import com.example.LocalSim.Model.*;
import com.example.LocalSim.Repository.*;
import com.example.LocalSim.Response.BaseResponse;

import com.example.LocalSim.Response.DocumentR;
import com.example.LocalSim.Response.DocumentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerSimService {

  @Autowired
  FlightInformationRepository flightInformationRepository;
  @Autowired
  CountryRepository countryRepository;

  @Autowired
  SimDetailsRepository simDetailsRepository;

  @Autowired
  CustomerDetailsRepository customerDetailsRepository;

  @Autowired
  DocumentRepository documentRepository;


  public BaseResponse getFlightInformation(String bookingId) {
    Optional<FlightInformationEntity> flightInformationEntityOptional =
        flightInformationRepository.findByBookingId(bookingId);
    FlightInformationEntity fl=flightInformationEntityOptional.get();
    if (flightInformationEntityOptional.isPresent())
      return BaseResponse.builder()
          .status(HttpStatus.OK.value())
          .data(fl)
          .build();
    else return BaseResponse.builder().status(HttpStatus.BAD_REQUEST.value()).build();
  }

  public BaseResponse getSimInformationFromCountry(String countryFrom) {
    CountryEntity countryEntity=countryRepository.findByCountryName(countryFrom)
            .orElseThrow(()->new EntityNotFoundException("No sim facility available in this country "));
    List<SimDetailsEntity> simDetailsEntities=simDetailsRepository.findAllByCountry(countryEntity);

      return BaseResponse.builder()
              .status(HttpStatus.OK.value())
              .data(simDetailsEntities)
              .build();

  }
  public BaseResponse uploadDocumentsInFolder(List<MultipartFile> multipartFiles, HttpServletRequest servletRequest, Integer customerId) throws IOException {
       String UPLOADED_FOLDER = "/home/deepanshu/Images";
    CustomerDetailsEntity customerDetailsEntity = customerDetailsRepository.findById(customerId).
            orElseThrow(() -> new EntityNotFoundException(String.format("Customer not found for id {}", customerId)));

    if (null != multipartFiles && multipartFiles.size() > 0) {
      List<String> fileNames = new ArrayList<String>();
      for (MultipartFile multipartFile : multipartFiles) {

        String fileName = multipartFile.getOriginalFilename();
        fileNames.add(fileName);
        //      Path path = Paths.get(UPLOADED_FOLDER + fileName);
        Path path = Paths.get(UPLOADED_FOLDER + "/" + multipartFile.getOriginalFilename());
        byte[] bytes = multipartFile.getBytes();
        Files.write(path, bytes);
//        File imageFile = new File(servletRequest.getServletContext().getRealPath("/image"), fileName);
//        try {
//          multipartFile.transferTo(imageFile);
//        } catch (IOException e) {
//          e.printStackTrace();
//        }
      }

    }
    return BaseResponse.builder().status(HttpStatus.OK.value()).message("done").build();
  }
  public BaseResponse showDocuments(Integer customerId) {
    CustomerDetailsEntity customerDetailsEntity = customerDetailsRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer Not found"));
    List<DocumentEntity> documentEntities = documentRepository.findAllByCountry(customerDetailsEntity.getDestinationTo());

//    List<DocumentR> documentRList=documentEntities.stream().map(documentEntity -> DocumentR.builder().name(documentEntity.getDocumentName()).build()).collect(Collectors.toList());
    DocumentResponse builder = DocumentResponse.builder().documentRList(documentEntities.stream().map
            (documentEntity -> {
              DocumentR documentR=DocumentR.builder().id(documentEntity.getId()).
                      information(documentEntity.getDocumentInfo()).name(documentEntity.getDocumentName()).build();
              return documentR;
            }).collect(Collectors.toList())).build();
    return BaseResponse.builder().status(HttpStatus.OK.value()).data(builder).build();
  }

  public BaseResponse saveDocuments(Integer customerId, boolean isOnArrivalVisa) {

    CustomerDetailsEntity customerDetailsEntity = customerDetailsRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer Not found"));
    customerDetailsEntity.setIsDocumentUpload(true);
    customerDetailsEntity.setIsOnArrivalVisa(isOnArrivalVisa);
    customerDetailsRepository.save(customerDetailsEntity);

    return BaseResponse.builder().status(HttpStatus.OK.value()).message("done").build();
  }

}
