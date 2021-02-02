package com.javatest.javawebapp.Service;

import com.javatest.javawebapp.model.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class JasonParsingService implements ParsingService{

    @Autowired
    RestTemplate restTemplate;

    @Override
    public api parse(String url) {
        return restTemplate.getForObject(url, api.class);
    }
}
