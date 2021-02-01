package com.javatest.javawebapp.web;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(path ="/api/v1/consume_api")
public class ConsumeAPIController{

    private final RestTemplate restTemplate;

    public ConsumeAPIController(RestTemplate restTemplate) {
        super();
        this.restTemplate = restTemplate;
    }

    @GetMapping
        public List<Object> getPrescriptions() {

            String url = "https://rxnav.nlm.nih.gov/REST/interaction/interaction.json?rxcui=341248";

            Object[] prescription = restTemplate.getForObject(url, Object[].class);
            return Arrays.asList(prescription);
        }
}

