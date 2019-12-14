package com.example.LocalSim.Service;

import com.example.LocalSim.Enum.PaymentStatus;
import com.example.LocalSim.Model.*;
import com.example.LocalSim.Repository.*;
import com.example.LocalSim.Response.BaseResponse;

import com.example.LocalSim.Response.DocumentR;
import com.example.LocalSim.Response.DocumentResponse;
import com.example.LocalSim.Response.FlightResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.UUID;
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
        if(!flightInformationEntityOptional.isPresent())
        {
            return BaseResponse.builder().status(400).message("Wrong request").build();
        }
        FlightInformationEntity fl = flightInformationEntityOptional.get();
        FlightResponse flightResponse = FlightResponse.builder().bookingId(fl.getBookingId()).countryFrom(fl.getCountryFrom().getCountryName())
                .countryTo(fl.getCountryTo().getCountryName()).destinationFrom(fl.getDestinationFrom()).
                        destinationTo(fl.getDestinationTo()).startTime(fl.getStartTime()).
                        endTime(fl.getEndTime()).userId(fl.getUser().getId()).build();


        if (flightInformationEntityOptional.isPresent())
            return BaseResponse.builder()
                    .status(HttpStatus.OK.value())
                    .data(flightResponse)
                    .build();
        else return BaseResponse.builder().status(400).message("Wrong request").build();
    }

    public BaseResponse getSimInformationFromCountry(String countryFrom) {
        CountryEntity countryEntity = countryRepository.findByCountryName(countryFrom)
                .orElseThrow(() -> new EntityNotFoundException("No sim facility available in this country "));
        List<SimDetailsEntity> simDetailsEntities = simDetailsRepository.findAllByCountry(countryEntity);

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

            }

        }
        return BaseResponse.builder().status(HttpStatus.OK.value()).message("done").build();
    }

    public BaseResponse showDocuments(Integer simId,String bookingId) {
      FlightInformationEntity flightInformationEntity=flightInformationRepository.findByBookingId(bookingId).
              orElseThrow(()->new EntityNotFoundException("flight not found"));
      SimDetailsEntity simDetailsEntity=simDetailsRepository.findById(simId).orElseThrow(()->new EntityNotFoundException("sim not found"));

        CustomerDetailsEntity customerDetailsEntity = CustomerDetailsEntity.builder().
                userEntity(flightInformationEntity.getUser()).flightInformationEntity(flightInformationEntity).
                simDetails(simDetailsEntity).destinationTo(flightInformationEntity.getCountryTo()).
                isDocumentUpload(false).paymentStatus(PaymentStatus.NOT_PAID).build();
        customerDetailsRepository.save(customerDetailsEntity);

        List<DocumentEntity> documentEntities = documentRepository.findAllByCountry(customerDetailsEntity.getDestinationTo());

//    List<DocumentR> documentRList=documentEntities.stream().map(documentEntity -> DocumentR.builder().name(documentEntity.getDocumentName()).build()).collect(Collectors.toList());
        DocumentResponse builder = DocumentResponse.builder().documentRList(documentEntities.stream().map
                (documentEntity -> {
                    DocumentR documentR = DocumentR.builder().id(documentEntity.getId()).
                            information(documentEntity.getDocumentInfo()).name(documentEntity.getDocumentName()).build();
                    return documentR;
                }).collect(Collectors.toList())).build();
        return BaseResponse.builder().status(HttpStatus.OK.value()).data(builder).build();
    }

    public BaseResponse paymentPaid(Integer customerId) {

        Boolean paymentPaid=true;
        CustomerDetailsEntity customerDetailsEntity = getCustomerDetails(customerId);
        if (paymentPaid == true) {
            customerDetailsEntity.setPaymentStatus(PaymentStatus.PAID);
        } else {
            customerDetailsEntity.setPaymentStatus(PaymentStatus.NOT_PAID);
        }
        customerDetailsEntity.setVerificationCode(generateVerificationCode(customerId));
        customerDetailsRepository.save(customerDetailsEntity);
        return BaseResponse.builder().status(HttpStatus.OK.value()).build();
    }

    private CustomerDetailsEntity getCustomerDetails(Integer customerId) {
        return customerDetailsRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer Not found"));

    }

  public BaseResponse saveDocuments(Integer customerId, boolean isOnArrivalVisa) {

    CustomerDetailsEntity customerDetailsEntity = customerDetailsRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer Not found"));
    customerDetailsEntity.setIsDocumentUpload(true);
    customerDetailsEntity.setIsOnArrivalVisa(isOnArrivalVisa);
    customerDetailsRepository.save(customerDetailsEntity);

    return BaseResponse.builder().status(HttpStatus.OK.value()).message("done").build();
  }

  private String generateVerificationCode(Integer customerId) {

    return UUID.randomUUID().toString().substring(0,6);
  }

  public BaseResponse renderResult(Integer customerId) {

    CustomerDetailsEntity customerDetailsEntity = customerDetailsRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer Not found"));
    String verificationCode = customerDetailsEntity.getVerificationCode();

    String result = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "\n" +
            "</head>\n" +
            "<body>\n" +
            "                <h4> <strong>Your application process has been completed.</strong></h4> <br><br>\n" +
            "                \n" +
            "               \n" +
            "                <ul>\n" +
            "                    <li >Please carry the hard copy of all the uploaded documents for physical verification.</li>\n" +
            "                    <li >Show the verification code "+verificationCode+" on the booth at the time of SIM collection.</li>\n" +
            "                    <li >Complete your physical verification and collect the SIM.</li>\n" +
            "                    \n" +
            "                </ul>\n" +
            "                \n" +
            "            </div> \n" +
            "\n" +
            "            \n" +
            "           \n" +
            "</body>\n" +
            "</html>\n";


    return BaseResponse.builder()
            .status(HttpStatus.OK.value())
            .data(result)
            .build();
  }

}
