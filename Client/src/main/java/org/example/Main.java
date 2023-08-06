package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Main {

    public final static int QUANTITY  = 1000;
    public final static Double MAX_VALUE = 45.0;

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        SensorDTO sensor = new SensorDTO("Sensor04");

        Random random = new Random();

        String registerSensorUrl = "http://localhost:8080/sensors/registration";
        String postMeasurementUrl = "http://localhost:8080/measurement/add";
        String getMeasurementsUrl = "http://localhost:8080/measurement";

        //register Sensor
        System.out.println(restTemplate.postForObject(registerSensorUrl,
                sensor,HttpStatus.class));

        //Adding measurement
        for (int i =0 ; i < QUANTITY; i++) {
            try {
                System.out.println(i + " - " + restTemplate.postForObject(postMeasurementUrl,
                        new HttpEntity<>(new MeasurementDTO(random.nextDouble() * MAX_VALUE,
                                random.nextBoolean(),
                                sensor)),HttpStatus.class));
            }catch (HttpClientErrorException e){
                System.out.println(i + " - Fail!");
            }
        }

        //Getting measurement
        ParameterizedTypeReference<List<MeasurementDTO>> typeReference =
                new ParameterizedTypeReference<List<MeasurementDTO>>() {};

        ResponseEntity<List<MeasurementDTO>> responseEntity = restTemplate.exchange(
                getMeasurementsUrl,
                HttpMethod.GET,
                null,
                typeReference
        );

        List<MeasurementDTO> measurementDTOS = responseEntity.getBody();

        ServerResponse response = new ServerResponse(measurementDTOS);

        if (!response.getMeasurementDTOS().isEmpty()) {
            for (int i =0 ; i < QUANTITY; i++) {
                System.out.println(i + " - " + response.getMeasurementDTOS().get(i).getValue());
            }
        }else {
            System.out.println(Collections.emptyList());
        }
    }
}