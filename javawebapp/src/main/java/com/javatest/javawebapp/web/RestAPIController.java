package com.javatest.javawebapp.web;


import com.javatest.javawebapp.Service.PrescriptionService;
import com.javatest.javawebapp.model.Prescription;
import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.List;

@RestController
@RequestMapping(path ="/api/v1/prescription")
public class RestAPIController {

    @Autowired
    private RestTemplate restTemplate;

    private final PrescriptionService prescriptionService;

    public RestAPIController(PrescriptionService prescriptionService) {
        super();
        this.prescriptionService = prescriptionService;
    }

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    @GetMapping("/consume_api")
    public List<Object> getPrescriptions() {

        String url = "http://localhost:8080/api/v1/prescription";

        Object[] prescription = restTemplate.getForObject(url, Object[].class);
        return Arrays.asList(prescription);
    }
}
